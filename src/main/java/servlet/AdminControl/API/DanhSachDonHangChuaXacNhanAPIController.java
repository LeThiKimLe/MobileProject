package servlet.AdminControl.API;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.ConnectDataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/api/manager/getOrderUnconfirmed"})
public class DanhSachDonHangChuaXacNhanAPIController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -779693565538978343L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("application/json;charset=UTF-8");
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
		
	}

}
