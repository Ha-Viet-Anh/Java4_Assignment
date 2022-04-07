package va.cookies;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.User;
import va.dao.UserDAO;

public class UserCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO;
    public UserCookie() {
        super();
        this.userDAO = new UserDAO();
    }

    public User getUserCookie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	Cookie[] cookie = request.getCookies();
		User user =null;
		if (cookie != null) {
			for (Cookie cc : cookie) {
				if (cc.getName().equals("user")) {
					String id = cc.getValue();
					user = userDAO.findById(Integer.parseInt(id));
				}
			}
		}
		return user;
	}


}
