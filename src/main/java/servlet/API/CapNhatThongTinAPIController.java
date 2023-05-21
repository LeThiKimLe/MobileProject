package servlet.API;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.GiaoVien;
import bean.HocVien;
import bean.QuanTriVien;
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

@WebServlet(urlPatterns = {"/api/general/updateProfile","/api/general/updateQTV","/api/general/updateGV"})
public class CapNhatThongTinAPIController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -378743862291982247L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String path = request.getContextPath() + request.getServletPath();

		Connection conn = null;

		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (path.contains("/api/general/updateProfile")){
			String maUser = (String) request.getParameter("maUser");
			HocVienAPI hvApi = new HocVienAPI();
			String checkUser = null;
			try {
				checkUser = DBUtils.KiemTraUser(conn, maUser);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if (checkUser == null) {
				hvApi.setResult("fail");
				hvApi.setMessage("Tài khoản không tồn tại");
			} else {
				String name= new String(request.getParameter("name").getBytes("UTF-8")) ;
				String sodt= (String)request.getParameter("sdt");
				String email= (String)request.getParameter("email");
				String ngaysinh = (String) request.getParameter("ngaysinh") ;
				String image = request.getParameter("image");
				
				Date date = new Date();
			    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				try {
					date = formatter.parse(ngaysinh);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				java.sql.Date ngaySinh = new java.sql.Date(date.getTime());
				
//				HocVien(String maHocVien, String tenHocVien, Date ngaySinh, String sdt, String email, String image)
				HocVien updateHocVien = new HocVien(checkUser, name, ngaySinh, sodt, email, image);
				
		        try {
		        	DBUtils.CapNhatThongTinUser(conn, updateHocVien);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        hvApi.setResult("success");
		        hvApi.setHocVien(updateHocVien);
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
		else if(path.contains("/api/general/updateQTV"))
		{
			String maqtv = (String) request.getParameter("MaQTV");
			QuanTriVienAPI qtvApi = new QuanTriVienAPI();
			
			 {
				String name= new String(request.getParameter("name").getBytes("UTF-8")) ;
				String sodt= (String)request.getParameter("sdt");
				String email= (String)request.getParameter("email");
				String diachi = (String) request.getParameter("diachi") ;
				String cccd = (String)request.getParameter("cccd");
				
				
				
//				HocVien(String maHocVien, String tenHocVien, Date ngaySinh, String sdt, String email, String image)
				QuanTriVien updateQTV = new QuanTriVien(maqtv, name, sodt, email,diachi, cccd);
				
		        try {
		        	DBUtils.CapNhatThongTinQTV(conn, updateQTV);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        qtvApi.setResult("success");
		        qtvApi.setQtv(updateQTV);
			}

			try {
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(response.getOutputStream(), qtvApi);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.contains("/api/general/updateGV"))
		{
			String magv = (String) request.getParameter("MaGV");
			GiaoVienAPI gvApi = new GiaoVienAPI();
			
			 {
				String name= new String(request.getParameter("name").getBytes("UTF-8")) ;
				String sodt= (String)request.getParameter("sdt");
				String email= (String)request.getParameter("email");
				String diachi = (String) request.getParameter("diachi") ;
				String cccd = (String)request.getParameter("cccd");
				
				
				
//				HocVien(String maHocVien, String tenHocVien, Date ngaySinh, String sdt, String email, String image)
				GiaoVien updateGV = new GiaoVien(magv,name,sodt,cccd,diachi,email);
				
		        try {
		        	DBUtils.CapNhatThongTinGV(conn, updateGV);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        gvApi.setResult("success");
		        gvApi.setGiaoVien(updateGV);
			}

			try {
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(response.getOutputStream(), gvApi);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
