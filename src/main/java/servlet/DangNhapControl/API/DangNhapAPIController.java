package servlet.DangNhapControl.API;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.DangNhap;
import bean.API.GiaoVienAPI;
import bean.API.HocVienAPI;
import bean.API.QuanTriVienAPI;
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

		String username = (String) request.getParameter("username");
		String password = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");

		DangNhap accountDangNhap = null;
		QuanTriVienAPI qtv = new QuanTriVienAPI();
		GiaoVienAPI gv = new GiaoVienAPI();
		HocVienAPI hv = new HocVienAPI();

		try {
			accountDangNhap = DBUtils.YeuCauDangNhap(conn, username, password);
			if (accountDangNhap != null && conn != null) {
				System.out.println(accountDangNhap.getRole());
				if (accountDangNhap.getRole().contains("QTV")) {
					qtv.setResult("success");
					qtv.setQtv(DBUtils.LayThongTinQTV(conn, accountDangNhap.getIdString()));
				} else if (accountDangNhap.getRole().contains("GV")) {
					gv.setResult("success");
					gv.setGiaoVien(DBUtils.LayThongTinGiaoVien(conn, accountDangNhap.getIdString()));
				} else if (accountDangNhap.getRole().contains("HV")) {
					hv.setResult("success");
					hv.setHocVien(DBUtils.LayThongTin(conn, accountDangNhap.getIdString()));
				}

			}else {
				String message = "Sai mật khẩu";
				if(DBUtils.CheckUsernameQTV(conn, username)) {
					qtv.setResult("fail");
					qtv.setMessage(message);
				}else if(DBUtils.CheckUsernameGiaoVien(conn, username)) {
					gv.setResult("fail");
					gv.setMessage(message);
				}else {
					hv.setResult("fail");
					hv.setMessage(message);
				}
			}
			ObjectMapper Obj = new ObjectMapper();

			if (accountDangNhap != null ) {
				if (accountDangNhap.getRole().contains("QTV")) {
					Obj.writeValue(response.getOutputStream(), qtv);
				} else if (accountDangNhap.getRole().contains("GV")) {
					Obj.writeValue(response.getOutputStream(), gv);
				} else {
					Obj.writeValue(response.getOutputStream(), hv);
				}
			}else if (DBUtils.CheckUsername(conn, username)) {
				if (DBUtils.CheckUsernameQTV(conn, username)) {
					Obj.writeValue(response.getOutputStream(), qtv);
				} else if (DBUtils.CheckUsernameGiaoVien(conn, username)) {
					Obj.writeValue(response.getOutputStream(), gv);
				} else {
					Obj.writeValue(response.getOutputStream(), hv);
				}
			} 
			else{
				Obj.writeValue(response.getOutputStream(), null);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
