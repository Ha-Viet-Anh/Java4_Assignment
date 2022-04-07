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

import entities.User;
import va.cookies.UserCookie;
import va.dao.UserDAO;
import va.ultis.EncryptUtil;

@WebServlet({
		"/home",
		"/user/login","/user/flogin","/user/logout",
		"/user/create","/user/store",
		"/user/change","/user/changePassword",
		"/user/edit","/user/update"
	})
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static boolean checkLogin; 
	private UserDAO userDAO;
	public User account;
	List<User> listUser;
	HttpSession session;
	private UserCookie userCookie;
	public HomeServlet() {
		super();
		this.userDAO = new UserDAO();
		this.account = new User();
		this.userCookie = new UserCookie();
		this.listUser = userDAO.all();
		checkLogin = false;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		session = request.getSession();
		this.account = userCookie.getUserCookie(request, response);
		
		if(uri.contains("home")) {
			this.home(request, response);
		}else if(uri.contains("flogin")) {
			this.flogin(request, response);
		}else if(uri.contains("index")) {
			this.index(request, response);
		}else if(uri.contains("create")) {
			this.create(request, response);
		}else if(uri.contains("logout")) {
			this.logOut(request, response);
		}else if(uri.contains("change")) {
			this.change(request, response);
		}else if(uri.contains("edit")) {
			this.edit(request, response);
		}else {
			
		}
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		session = request.getSession();
		String uri = request.getRequestURI();
		if(uri.contains("store")) {
			this.store(request, response);
		}else if(uri.contains("login")) {
			this.login(request, response);
		}else if(uri.contains("changePassword")) {
			this.changePassword(request, response);
		}else if(uri.contains("update")) {
			this.update(request, response);
		}else {
			
		}
	}
	
	private void home(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		account = userCookie.getUserCookie(request, response);
//		request.setAttribute("user", account);		
		request.setAttribute("view", "/views/home.jsp");
	}
	private void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	private void create(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("view", "/views/user/create.jsp");
		
	}
	private void store(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		session.setMaxInactiveInterval(1);
		try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			
			for (int i = 0; i < listUser.size(); i++) {
				if(listUser.get(i).getEmail().equalsIgnoreCase(email)) {
					response.sendRedirect("/PH14045_HaVietAnh_Assignment/user/create");
					session.setAttribute("error","Email đã được đăng ký!");
					return;
				}
			}
			user.setPassword(EncryptUtil.encrypt(password));
			this.userDAO.create(user);
			session.setAttribute("message","Đăng ký thành công!");
			response.sendRedirect("/PH14045_HaVietAnh_Assignment/user/create");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error","Đăng ký thất bại!");
			response.sendRedirect("/PH14045_HaVietAnh_Assignment/user/create");
		}
		
	}
	private void flogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("view", "/views/user/login.jsp");
	}
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {	
			session.setMaxInactiveInterval(10*60*60);
			String email = request.getParameter("login_email"),
					pwd = request.getParameter("login_password");
			if(this.userDAO.findByEmail(email)!=null) {
				account = this.userDAO.findByEmail(email);
			}
			String id = String.valueOf(account.getId());
			boolean check = EncryptUtil.check(pwd, account.getPassword());
			if (check == true) {
				try {
					Cookie cookie = new Cookie("user", id);
					cookie.setMaxAge(2*60*60);
					cookie.setPath("/");
					response.addCookie(cookie);
				} catch (Exception e) {
					e.printStackTrace();
				}
				session.setAttribute("user", account);
				response.sendRedirect("/PH14045_HaVietAnh_Assignment/home");
			}else {
				
				response.sendRedirect("/PH14045_HaVietAnh_Assignment/user/flogin");
			}
		} catch (Exception e) {
			session.setAttribute("loginFailse","Tài khoản hoặc mật khẩu không đúng!");
			response.sendRedirect("/PH14045_HaVietAnh_Assignment/user/flogin");
		}
		
	}
	private void logOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		session.setMaxInactiveInterval(1);
		if(cookies!=null) {
			for (Cookie cc: cookies) {
				if(cc.getName().equals("user")) {
					Cookie cookie = new Cookie("user","");
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
					session.setAttribute("loginSuccess","Đăng xuất thành công!");
					session.removeAttribute("user");
				}
			}
		}
		this.home(request, response);
	}
	private void change(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		account = userCookie.getUserCookie(request, response);
		request.setAttribute("userDMK", account);
		request.setAttribute("view", "/views/user/changePass.jsp");
	}
	private void changePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String passOld = request.getParameter("passOld");
		String passNew = request.getParameter("passNew");
		String checkPass = request.getParameter("checkPass");
		session.setMaxInactiveInterval(1);
		try {
			account = userCookie.getUserCookie(request, response);
			boolean check = EncryptUtil.check(passOld,account.getPassword());			
			if(check==true) {
				if(passNew.equals(checkPass)) {
					account.setPassword(EncryptUtil.encrypt(passNew));
					session.setAttribute("changeSuccess","Đổi mật khẩu thành công!");
				}else{
					session.setAttribute("changeError","Mật khẩu không trùng khớp!");
				}
			}else {
				session.setAttribute("changeError","Mật khẩu cũ không đúng!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/PH14045_HaVietAnh_Assignment/user/change");
	}
	private void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		account = userCookie.getUserCookie(request, response);
		request.setAttribute("userUpdate", account);
		request.setAttribute("view", "/views/user/update.jsp");
	}
	private void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		account = userCookie.getUserCookie(request, response);
		User userNew = new User();
		session.setMaxInactiveInterval(1);
		try {
			BeanUtils.populate(userNew, request.getParameterMap());
			userNew.setEmail(account.getEmail());
			userNew.setPassword(account.getPassword());
			
			this.userDAO.update(userNew);
			try {
				String id = String.valueOf(account.getId());
				Cookie cookie = new Cookie("user","");
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.setAttribute("updateSuccess","Update thành công vui lòng đăng nhập lại!");
			response.sendRedirect("/PH14045_HaVietAnh_Assignment/user/edit");
		} catch (Exception e) {
			session.setAttribute("updateFail","Update thất bại!");
			response.sendRedirect("/PH14045_HaVietAnh_Assignment/user/edit");
			e.printStackTrace();
		}

	}
}
