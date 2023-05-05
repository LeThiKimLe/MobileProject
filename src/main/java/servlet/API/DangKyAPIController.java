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
import dao.ConnectDataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtils;

@WebServlet(urlPatterns = { "/api/general/signup" })
public class DangKyAPIController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6416881327821559457L;

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

		String username = (String) request.getParameter("username");
		HocVienAPI hvApi = new HocVienAPI();
		boolean checkUser = true;
		try {
			checkUser = DBUtils.CheckUsername(conn, username);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (!checkUser) {
			hvApi.setResult("fail");
			hvApi.setMessage("Tài khoản tồn tại");
		} else {
			String name= new String(request.getParameter("name").getBytes("UTF-8")) ;
			String sodt= (String)request.getParameter("sdt");
			String email= (String)request.getParameter("email");
			String ngaysinh = (String) request.getParameter("ngaysinh") ;
			Date date = new Date();
		    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			if(ngaysinh == "") {
				ngaysinh = formatter.format(date);
			}
			String passString= (String)request.getParameter("password");
			
			try {
				date = formatter.parse(ngaysinh);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date ngaySinh = new java.sql.Date(date.getTime());
			
			HocVien signInHocVien = new HocVien(name, ngaySinh, sodt, email);
			DangNhap accountDangKy = new DangNhap(username, passString);
	        
	        try {
	        	signInHocVien.setMaHocVien(signInHocVien.autoID(conn));
	        	accountDangKy.setIdString(accountDangKy.autoID());
				DBUtils.YeuCauDangKy(conn, signInHocVien, accountDangKy);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        hvApi.setResult("success");
	        hvApi.setHocVien(signInHocVien);
		}

		try {
			ObjectMapper obj = new ObjectMapper();
			conn.close();
			obj.writeValue(response.getOutputStream(), hvApi);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
