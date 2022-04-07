package va.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import va.cookies.UserCookie;
import va.dao.CategoryDAO;
import va.dao.UserDAO;
import entities.Category;
import entities.User;

@WebServlet({ "/category", "/admin/category/create", "/admin/category/store", 
	"/admin/category/edit", "/admin/category/update", "/admin/category/delete" })
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private CategoryDAO categoryDAO;
	private User account;
	Cookie[] cookie;
	private UserCookie userCookie;
	public CategoryServlet() {
		super();
		this.userDAO = new UserDAO();
		this.categoryDAO = new CategoryDAO();
		this.account = new User();
		this.userCookie = new UserCookie();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		HttpSession session= request.getSession();
		account=userCookie.getUserCookie(request, response);
		if(account==null) {
			session.setMaxInactiveInterval(1);
			session.setAttribute("notAcc","Vui lòng đăng nhập!");
			request.setAttribute("view", "/views/user/login.jsp");
			
		}else if(account!=null&&account.getRole()==0&&uri.contains("/admin/")) {
			session.setAttribute("notAcc","Vui lòng đăng nhập bằng admin!");
			request.setAttribute("view", "/views/user/login.jsp");
		}else if (uri.contains("create")) {
			this.create(request, response);
		}else if (uri.contains("edit")) {
			this.edit(request, response);
		}else if (uri.contains("delete")) {
			this.delete(request, response);
		}
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		if (uri.contains("store")) {
			this.store(request, response);
		}else if (uri.contains("update")) {
			this.update(request, response);
		}
	}

	private void create(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> listCate = this.categoryDAO.all();
		this.account = userCookie.getUserCookie(request, response);
		request.setAttribute("user", account);
		request.setAttribute("listCate", listCate);
		request.setAttribute("view", "/views/admin/categories/create.jsp");
	}
	
	private void store(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category cate = new Category();
		String ten = request.getParameter("ten");
		this.account = userCookie.getUserCookie(request, response);
		cate.setTen(ten);
		cate.setUser(account);
		try {
			this.categoryDAO.create(cate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/PH14045_HaVietAnh_Assignment/admin/category/create");
	}
	private void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Category cate= categoryDAO.findById(id);
		this.account = userCookie.getUserCookie(request, response);
		request.setAttribute("user", account);
		request.setAttribute("cate", cate);
		request.setAttribute("view", "/views/admin/categories/update.jsp");
	}
	private void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category cate = new Category();
		int id = Integer.parseInt(request.getParameter("id"));
		Category cateOld= categoryDAO.findById(id);
		try {
			BeanUtils.populate(cate, request.getParameterMap());
			cate.setUser(cateOld.getUser());
			this.categoryDAO.update(cate);
			response.sendRedirect("/PH14045_HaVietAnh_Assignment/admin/category/create");
		} catch (Exception e) {
			response.sendRedirect("/PH14045_HaVietAnh_Assignment/admin/category/edit");
			e.printStackTrace();
		}
	}
	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Category cate = categoryDAO.findById(id);
		try {
			this.categoryDAO.delete(cate);
			this.create(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}