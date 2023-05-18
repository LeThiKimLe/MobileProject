package servlet.API;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.GiaoDich;
import bean.ViThanhToan;
import bean.API.FeedbackAPI;
import bean.API.HocVienAPI;
import bean.API.SoDuAPI;
import bean.API.ViThanhToanAPI;
import dao.ConnectDataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtils;

@WebServlet(urlPatterns = { "/api/student/getWallet"})
public class GiaoDichAPIController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 356186268164869556L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("application/json;charset=UTF-8");
		String path = req.getContextPath() + req.getServletPath();
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
		
		String maHocVien = req.getParameter("maHocVien");
		
		if (path.contains("/api/student/getWallet")) { 
			try {
				
				ViThanhToanAPI viThanhToanAPI= new ViThanhToanAPI();
				String checkUser= null;
				try {
					checkUser = DBUtils.KiemTraUser(conn, maHocVien);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if (checkUser == null) {
					viThanhToanAPI.setResult("fail");
					viThanhToanAPI.setMessage("Tài khoản không tồn tại");
				} 
				else
				{
					ViThanhToan myWallet = new ViThanhToan();
					myWallet.LayVi(conn, maHocVien);
					List<GiaoDich> listGD= DBUtils.getTransaction(conn, myWallet.getMaVi());
					viThanhToanAPI.setResult("success");
					viThanhToanAPI.setWallet(myWallet);
					viThanhToanAPI.setListGD(listGD);
				}
				
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(resp.getOutputStream(), viThanhToanAPI);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
