package servlet.DangNhapControl.API;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.DangNhap;
import bean.API.HocVienAPI;
import dao.ConnectDataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtils;

@WebServlet(urlPatterns = { "/api/general/login" })
public class DangNhapAPIController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 272203276979387478L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		String username = (String) request.getParameter("username");
		String password = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");

		DangNhap accountDangNhap = null;
		HocVienAPI hvApi = new HocVienAPI();

		try {
			accountDangNhap = DBUtils.YeuCauDangNhap(conn, username, password);
			if (accountDangNhap != null && conn != null) {
				hvApi.setResult("success");
				hvApi.setHocVien(DBUtils.LayThongTin(conn, accountDangNhap.getIdString()));
			} else {
				hvApi.setResult("fail");
				if(!DBUtils.CheckUsername(conn, username)) {
					hvApi.setMessage("Mật khẩu sai");
				}
				else {
					hvApi.setMessage("Tài khoản và mật khẩu không tồn tại");
				}
			}
			ObjectMapper Obj = new ObjectMapper();
			conn.close();
			Obj.writeValue(response.getOutputStream(), hvApi);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
