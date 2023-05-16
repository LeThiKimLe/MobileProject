package servlet.API;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.DangNhap;
import bean.HocVien;
import bean.API.HocVienAPI;
import bean.API.SoDuAPI;
import dao.ConnectDataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtils;

@WebServlet(urlPatterns = { "/api/student/getBalance" })
public class ThanhToanAPIController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("application/json;charset=UTF-8");
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
		
		String maUser = (String) request.getParameter("maUser");
		HocVienAPI hvApi = new HocVienAPI();
		String checkUser = null;
		SoDuAPI new_budget= new SoDuAPI();
		try {
			checkUser = DBUtils.KiemTraUser(conn, maUser);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (checkUser == null) {
			new_budget.setResult("fail");
			new_budget.setMessage("Tài khoản không tồn tại");
		} else 
		{	
			try {
				new_budget= DBUtils.LaySoDuVi(conn, maUser);
				new_budget.setResult("success");
				new_budget.setMessage("Lấy số dư thành công");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			ObjectMapper obj = new ObjectMapper();
			conn.close();
			obj.writeValue(response.getOutputStream(), new_budget);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
