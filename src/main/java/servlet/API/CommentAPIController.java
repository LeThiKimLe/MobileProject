package servlet.API;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.ConnectDataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/api/general/getComment","/api/general/sendComment", "/api/general/repComment"})
public class CommentAPIController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2095665454976172227L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		Connection conn = null;
		String path = req.getContextPath() + req.getServletPath();

		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(path.contains("/api/general/getComment")) {
			
		}
	}
	
	
}
