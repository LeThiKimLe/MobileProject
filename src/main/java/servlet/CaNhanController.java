package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.catalina.valves.AbstractAccessLogValve;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import utils.DBUtils;
import bean.*;
import dao.ConnectDataBase;
/**
 * Servlet implementation class CaNhanController
 */
@WebServlet(name="mypage", urlPatterns = {"/mypage"})
public class CaNhanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaNhanController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		String roleString=null;
		
		Cookie[] cookies = request.getCookies();
    	if(cookies != null)
    	{
    		
    		Connection conn = null;
    		try 
    		{
    			conn = ConnectDataBase.getConnection();
    		} 
    		catch (SQLException e1)
    		{
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
	    	for(Cookie cookie : cookies)
	    	{
	    		if(cookie.getName().equals("role"))
	    		{
	    			roleString=cookie.getValue();
	    			break;
	    		}
	    	}
    		for(Cookie cookie : cookies)
	    	{
	    		if (cookie.getName().equals("userID"))
	    		{
	    			
	    			if (roleString.equals("HV"))
	    			{	
	    				HocVien hvHocVien=null;
						try
						{
							hvHocVien = DBUtils.LayThongTin(conn,cookie.getValue());
						} catch (SQLException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    				BigDecimal soduvi=null;
						try
						{
							soduvi = DBUtils.LaySoDu(conn, cookie.getValue());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    				request.setAttribute("HocVien", hvHocVien);
	    				request.setAttribute("SoDuVi", soduvi);
	    				RequestDispatcher dispatcher = request.getServletContext()
	    		                    .getRequestDispatcher("/WEB-INF/Pages/MyPage.jsp");
	    		        dispatcher.forward(request, response);
	    			}
	    			else if (roleString.equals("GV")) 
	    			{
	    				GiaoVien gVien=null;
	    				try {
	    					System.out.print(cookie.getValue());
							gVien= new GiaoVien(conn, (String)cookie.getValue());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    				HttpSession session = request.getSession();
	    				session.setAttribute("teaching_course", gVien.getListKhoaHocs());
	    				request.setAttribute("GiaoVien", gVien);
	    				RequestDispatcher dispatcher = request.getServletContext()
	    		                    .getRequestDispatcher("/WEB-INF/Pages/TeacherPage.jsp");
	    		        dispatcher.forward(request, response);
	    			}
	    			else if (roleString.equals("QTV"))
	    			{
	    				response.sendRedirect(request.getContextPath() +"/admin");
	    			}
	    		}
	    	}
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
