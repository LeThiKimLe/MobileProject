package servlet.TeacherController.API;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.HocVien;
import bean.API.ThongKeHocVienAPI;
import dao.ConnectDataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUntilQLGV;

@WebServlet(urlPatterns = {"/api/teacher/getStastics"})
public class ThongKeHocVienAPIController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -898348753268786436L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		
		String maNguoiDung = req.getParameter("maNguoiDung"); //mã giảng viên
		String maBaiHoc = req.getParameter("maBaiHoc");
		String maKhoaHoc = req.getParameter("maKhoaHoc");
		
		int slHocVien = 0;
		List<HocVien> listHv = null;
		ThongKeHocVienAPI thongKe = new ThongKeHocVienAPI();
		Boolean checkGiaoVien = true;
		
		try {
			checkGiaoVien = DBUntilQLGV.checkMaGiaoVien(conn, maNguoiDung);
			slHocVien = DBUntilQLGV.SoLuongHocVienDangKyGiaoVien(conn, maNguoiDung, maKhoaHoc, maBaiHoc);
			listHv = DBUntilQLGV.DanhSachHocVienDangKyGiaoVien(conn, maNguoiDung, maKhoaHoc, maBaiHoc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!checkGiaoVien) {
			thongKe.setResult("fail");
		}else {
			thongKe.setResult("success");
			thongKe.setSoNguoiHoc(slHocVien);
			thongKe.setDanhSach(listHv);
		}
		
		try {
			ObjectMapper obj = new ObjectMapper();
			conn.close();
			obj.writeValue(resp.getOutputStream(), thongKe);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
