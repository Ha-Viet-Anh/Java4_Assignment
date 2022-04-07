package va.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import entities.User;
import va.cookies.UserCookie;
import va.dao.UserDAO;
import va.ultis.EncryptUtil;

@WebServlet({ "/admin/create", "/admin/store", "/admin/edit", "/admin/update", "/admin/delete", })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private User account;
	private UserCookie userCookie;
	List<User> listUser;
	public UserServlet() {
		super();
		this.userDAO = new UserDAO();
		this.account = new User();
		this.userCookie = new UserCookie();
		this.listUser = userDAO.all();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		HttpSession session= request.getSession();
		account=userCookie.getUserCookie(request, response);
//		
//		if(account==null) {
//			session.setMaxInactiveInterval(1);
//			session.setAttribute("notAcc","Vui lòng đăng nhập!");
//			request.setAttribute("view", "/views/user/login.jsp");
//			
//		}else if(account!=null&&account.getRole()==0&&uri.contains("/admin/")) {
//			session.setAttribute("notAcc","Vui lòng đăng nhập bằng admin!");
//			request.setAttribute("view", "/views/user/login.jsp");
//		}
		if(uri.contains("create")) {
			this.create(request, response);
		}else if(uri.contains("edit")) {
			this.edit(request, response);
		}else if(uri.contains("delete")) {
			this.delete(request, response);
		}else {
			
		}
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		if(uri.contains("store")) {
			this.store(request, response);
		}else if(uri.contains("update")) {
			this.update(request, response);
		}else {
			
		}
	}
	private void create(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		listUser = userDAO.all();
		request.setAttribute("list", listUser);
		request.setAttribute("view", "/views/admin/users/create.jsp");
	}
	private void store(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
		try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			for (int i = 0; i < listUser.size(); i++) {
				if(listUser.get(i).getEmail().equalsIgnoreCase(email)) {
					response.sendRedirect("/PH14045_HaVietAnh_Assignment/admin/create");
					return;
				}
			}
			user.setPassword(EncryptUtil.encrypt(password));
			this.userDAO.create(user);
			response.sendRedirect("/PH14045_HaVietAnh_Assignment/admin/create");	
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/PH14045_HaVietAnh_Assignment/admin/create");
		}
	}
	private void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("view", "/views/admin/users/update.jsp");
		int id = Integer.parseInt(request.getParameter("id"));
		User user = userDAO.findById(id);
		request.setAttribute("user", user);
	}
	private void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String password = request.getParameter("password");
		int id = Integer.parseInt(request.getParameter("id"));
		User userNew = new User();
		User userOld = userDAO.findById(id);
		try {
			BeanUtils.populate(userNew, request.getParameterMap());
			userNew.setEmail(userOld.getEmail());
			userNew.setPassword(EncryptUtil.encrypt(password));
			this.userDAO.update(userNew);
			response.sendRedirect("/PH14045_HaVietAnh_Assignment/admin/create");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		this.account = this.userCookie.getUserCookie(request, response);
		try {
			User user = userDAO.findById(id);
			this.userDAO.delete(user);
			this.create(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
