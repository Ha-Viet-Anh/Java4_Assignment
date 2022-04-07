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

import entities.Category;
import entities.Product;
import entities.User;
import va.cookies.UserCookie;
import va.dao.CategoryDAO;
import va.dao.ProductDAO;

@WebServlet({"/admin/product/create","/admin/product/store","/admin/product/edit","/admin/product/update","/admin/product/delete"})
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;
    private UserCookie userCookie;
    private User account;
    public ProductServlet() {
        super();
        this.productDAO = new ProductDAO();
        this.userCookie = new UserCookie();
        this.categoryDAO = new CategoryDAO();
        this.account = new User();
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
		}else {
			
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
		} else if (uri.contains("update")) {
			this.update(request, response);
		} else {
			
		}
	}
	private void create(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Product> listSP = productDAO.all();
		List<Category> listCate = categoryDAO.all();
		request.setAttribute("listSP", listSP);
		request.setAttribute("cate", listCate);
		request.setAttribute("view", "/views/admin/products/create.jsp");
	}
	private void store(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Product product = new Product();
		try {
			BeanUtils.populate(product, request.getParameterMap());
			this.account = userCookie.getUserCookie(request, response);
			product.setNameSell(account.getHoTen());
			this.productDAO.create(product);
			response.sendRedirect("/PH14045_HaVietAnh_Assignment/admin/product/create");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		List<Category> listCate = categoryDAO.all();
		Product product = productDAO.findById(id);
		request.setAttribute("product", product);
		request.setAttribute("cate", listCate);
		request.setAttribute("view", "/views/admin/products/update.jsp");
	}
	private void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Product productNew = new Product();
		Product productOld = productDAO.findById(id);
		try {
			String name=request.getParameter("ten");
			BeanUtils.populate(productNew, request.getParameterMap());
			productNew.setCategoryId(productOld.getCategoryId());
			productNew.setNameSell(productOld.getNameSell());
			this.productDAO.update(productNew);
			
			response.sendRedirect("/PH14045_HaVietAnh_Assignment/admin/product/create");
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("/PH14045_HaVietAnh_Assignment/admin/product/edit");
		}
		
	}
	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Product pro = productDAO.findById(id);
			this.productDAO.delete(pro);
			this.create(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
