package servlet.CourseControl.API;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.API.ThongKeSoLuongHocVienDangKyAPI;
import dao.ConnectDataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUntilQLGV;
import utils.DBUntilQLKH;

@WebServlet(urlPatterns = {"/api/manager/getStatistics"})
public class ThongKeSoLuongHocVienDangKyKhoaHoc extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2641503214906297289L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		Connection conn = null;

		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ThongKeSoLuongHocVienDangKyAPI thongKe = new ThongKeSoLuongHocVienDangKyAPI();
		try {
			thongKe.setTongSoHocVien(DBUntilQLKH.SoLuongHocVien(conn));
			thongKe.setDangKyNhieu(DBUntilQLGV.DanhSachSoLuongHocVienDangKyKhoaHoc(conn));
			conn.close();
			ObjectMapper obj = new ObjectMapper();
			obj.writeValue(resp.getOutputStream(), thongKe);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
