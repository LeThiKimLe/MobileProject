package servlet.CartController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.PageContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bean.*;
import java.util.List;
/**
 * Servlet implementation class GioHangController
 */
@WebServlet(name="cart",urlPatterns = { "/cart" })
public class GioHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GioHangController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");
		if (action == null) {
			doGet_DisplayCart(request, response);
		} else {
			if (action.equalsIgnoreCase("buy")) {
				doGet_Buy(request, response);
			} else if (action.equalsIgnoreCase("remove")) {
				doGet_Remove(request, response);
			}
		}
	}
	
	protected void doGet_DisplayCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/Pages/GioHang.jsp").forward(request, response);
	}

	protected void doGet_Remove(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<DoTrongGioHang> cart = (List<DoTrongGioHang>)session.getAttribute("cart");
		int index = isExisting(request.getParameter("maKhoaHoc"), cart);
		cart.remove(index);
		session.setAttribute("cart", cart);
		response.sendRedirect("cart");
	}

	protected void doGet_Buy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		if (session.getAttribute("cart") == null) {
			List<DoTrongGioHang> cart = new ArrayList<DoTrongGioHang>();
			
			cart.add(new DoTrongGioHang(request.getParameter("maKhoaHoc")));
			session.setAttribute("cart", cart);
			request.setAttribute("state", "success");
		} else {
			List<DoTrongGioHang> cart = (List<DoTrongGioHang>) session.getAttribute("cart");
			int index = isExisting(request.getParameter("maKhoaHoc"), cart);
			if (index == -1) {
				cart.add(new DoTrongGioHang(request.getParameter("maKhoaHoc")));
				session.setAttribute("cart", cart);
				request.setAttribute("state", "success");
			}
			else 
			{
				request.setAttribute("state", "fail");
			}
		}
		response.setContentType("text/html;charset=UTF-8");
	     RequestDispatcher dispatcher2= request.getServletContext().getRequestDispatcher("/course-detail");
	     dispatcher2.forward(request, response);
	}
	
	private int isExisting(String id, List<DoTrongGioHang> cart) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getItem().getMaKhoaHoc().equalsIgnoreCase(id)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
