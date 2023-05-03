package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.DoTrongGioHang;
import bean.DonHang;
import bean.HocVien;
import bean.KhoaHoc;
import dao.ConnectDataBase;

/**
 * Servlet implementation class ThanhToanController
 */
@WebServlet (name="pay", urlPatterns = {"/pay"})
public class ThanhToanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThanhToanController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Cookie[] cookies = request.getCookies();
		String maHocVienString="";
		if(cookies !=null)
		{
			for(Cookie cookie : cookies)
				if(cookie.getName().equals("userID"))
				{
						maHocVienString=(String)(cookie.getValue());
						break;
				}
		}
		long millis = System.currentTimeMillis();
	    java.sql.Date today = new java.sql.Date(millis);
		DonHang donHang=null;
		try {
			donHang = new DonHang(conn,maHocVienString, today);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		
		int index=-1;
		List<KhoaHoc> list_course=null;
		List<DoTrongGioHang> cart =null;
		if (session.getAttribute("registed_course") == null) {
			list_course = new ArrayList<KhoaHoc>();
		} else {
			list_course = (List<KhoaHoc>)session.getAttribute("registed_course");
		}
		if (session.getAttribute("cart") != null)
		{
			cart = (List<DoTrongGioHang>) session.getAttribute("cart");
			if(cookies !=null)
			{
				for(Cookie cookie : cookies)
				{
					index=isExisting((String)cookie.getName(), cart);
					if(index!=-1)
					{
							try
							{
								donHang.ThemItem(conn, cart.get(index).getItem().getMaKhoaHoc(), cart.get(index).getItem().getGiaTien());
								list_course.add(cart.get(index).getItem());
								cart.remove(index);
								
							} catch (SQLException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				}
			}
		}
			try {
				donHang.HoanTatHoaDon(conn);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			HocVien curHocVien= new HocVien(maHocVienString);
			try
			{
				curHocVien.ThanhToan(conn, donHang.getTongSoTien(), donHang.getMaDonHang());
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			session.setAttribute("cart", cart);
			session.setAttribute("registed_course", list_course);
			request.setAttribute("state", "success");
			
			if(cookies !=null)
			{
				for(Cookie cookie : cookies)
					if(cookie.getName().equals("soduvi"))
					{
							cookie.setValue(String.valueOf(curHocVien.LaySoDuVi(conn)));
							response.addCookie(cookie);
							break;
					}
			}
			
			response.setContentType("text/html;charset=UTF-8");
		    RequestDispatcher dispatcher2= request.getServletContext().getRequestDispatcher("/cart");
		    dispatcher2.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private int isExisting(String id, List<DoTrongGioHang> cart)
	{
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getItem().getMaKhoaHoc().equalsIgnoreCase(id)) {
				return i;
			}
		}
		return -1;
	}
}
