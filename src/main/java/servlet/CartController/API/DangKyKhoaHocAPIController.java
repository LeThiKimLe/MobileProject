package servlet.CartController.API;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.API.DangKyKHMessageAPI;
import dao.ConnectDataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUntilQLKH;

@WebServlet(urlPatterns = { "/api/student/regisCourse" })
public class DangKyKhoaHocAPIController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5985911278883950976L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		String maHocVien = req.getParameter("maHocVien");
		String cacKhoaHoc = req.getParameter("cacKhoaHoc");

		List<String> listKhoaHocDK = new ArrayList<String>(
				Arrays.asList(cacKhoaHoc.replaceAll("\\s|\\[|\\]", "").split(",")));

		DangKyKHMessageAPI dk = new DangKyKHMessageAPI();
		Boolean checkHocVien = true;
		Boolean checkKhoaHoc = true;
		Boolean checkDaDangKy = false;

		try {
			checkHocVien = DBUntilQLKH.CheckHocVien(conn, maHocVien);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!checkHocVien) {
			dk.setResult("fail");
			dk.setMessage("Học viên không tồn tại");
		} else {
			for (String string : listKhoaHocDK) {
				try {
					checkKhoaHoc = DBUntilQLKH.CheckCourse(conn, string);
					checkDaDangKy = DBUntilQLKH.CheckDaDangKy(conn, maHocVien, string);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!checkKhoaHoc) {
					dk.setResult("fail");
					dk.setMessage("Khóa học " + string + " không tồn tại hoặc chưa trong trạng thái duyệt");
					break;
				} else if (checkDaDangKy) {
					dk.setResult("fail");
					dk.setMessage("Khóa học " + string + " học viên đã đăng ký");
					break;
				}
			}
			if (checkHocVien && checkKhoaHoc && !checkDaDangKy) {
				for (String string : listKhoaHocDK) {
					try {
						DBUntilQLKH.DangKy(conn, maHocVien, string);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				dk.setResult("success");
				dk.setMessage("Đã gửi yêu cầu thành công");
			}
		}
		try {
			ObjectMapper obj = new ObjectMapper();
			obj.writeValue(resp.getOutputStream(), dk);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
