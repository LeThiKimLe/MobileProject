package servlet.CourseControl.API;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.BaiHoc;
import bean.API.BaiHocAPI;
import bean.API.KhoaHocGiaoVienAPI;
import bean.API.KhoaHocHocVienAPI;
import dao.ConnectDataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUntilQLGV;
import utils.DBUntilQLKH;

@WebServlet(urlPatterns = { "/api/student/myCourse", "/api/student/myCourse/info", "/api/teacher/myCourse" })
public class KhoaHocUserAPIController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5917769346713436141L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		Connection conn = null;
		String path = req.getContextPath() + req.getServletPath();

		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (path.contains("/api/student/myCourse/info")) {
			String maHocVien = req.getParameter("maHocVien");
			String maKhoaHoc = req.getParameter("maKhoaHoc");
			BaiHocAPI bh = new BaiHocAPI();
			List<BaiHoc> listBH = new ArrayList<BaiHoc>();
			try {
				listBH = DBUntilQLKH.DanhSachBaiHocVien(conn, maKhoaHoc, maHocVien);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (listBH == null) {
				bh.setResult("fail");
				bh.setMessage("Học viên chưa đăng ký khóa học hoặc bài học chưa được phát hành");
			} else {
				bh.setResult("success");
				bh.setLessons(listBH);
			}
			try {
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(resp.getOutputStream(), bh);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (path.contains("/api/student/myCourse")) {
			String maHocVien = req.getParameter("maHocVien");
			List<KhoaHocHocVienAPI> khhv = new ArrayList<KhoaHocHocVienAPI>();

			try {
				khhv = DBUntilQLKH.DanhSachKhoaHocHocVien(conn, maHocVien);
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(resp.getOutputStream(), khhv);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String maGiaoVien = req.getParameter("maGiaoVien");
			List<KhoaHocGiaoVienAPI> khgv = new ArrayList<KhoaHocGiaoVienAPI>();
			try {
				khgv = DBUntilQLGV.DanhSachKhoaHocGiaoVien(conn, maGiaoVien);
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(resp.getOutputStream(), khgv);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
