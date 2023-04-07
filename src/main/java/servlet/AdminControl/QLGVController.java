package servlet.AdminControl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUntilQLGV;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bean.GiaoVien;

import dao.ConnectDataBase;
import java.io.IOException;

/**
 * Servlet implementation class QLGVController
 */

@WebServlet (name="QLGV", urlPatterns = {"/QLGV"})
public class QLGVController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	public QLGVController () {
		super();
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 List<GiaoVien> list = null;
	        try {
	            list = DBUntilQLGV.listGiaoVien(conn);
	            
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	           
	        }
	       request.setAttribute("QLGV", list);
		
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Pages/QLGV.jsp");
		dispatcher.forward(request, response);
        
       
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
