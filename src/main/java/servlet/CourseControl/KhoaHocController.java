package servlet.CourseControl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bean.*;
import dao.ConnectDataBase;

/**
 * Servlet implementation class KhoaHocController
 */
@WebServlet(name="courses",urlPatterns = { "/courses" })
public class KhoaHocController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KhoaHocController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		
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
		
		List<KhoaHoc> list = null;
	
		try
		{
			list = DBUtils.LayAllKhoaHoc(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 request.setAttribute("khoahocList", list);
		 
		 List<KhoiLop> listlop = null;
			
		 try
		 {
			listlop = DBUtils.LayAllKhoiLop(conn);
			System.out.print("Lay khoi lớp thanh cong");
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 request.setAttribute("khoilopList", listlop);
         
        // Forward sang /WEB-INF/views/productListView.jsp
       response.setContentType("text/html;charset=UTF-8");
       RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/Pages/KhoaHoc.jsp");
        dispatcher.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
