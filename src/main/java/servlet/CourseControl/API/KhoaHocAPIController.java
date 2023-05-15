package servlet.CourseControl.API;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.GiaoVien;
import bean.KhoaHoc;
import bean.KhoiLop;
import bean.PhanMon;
import bean.ThongKeHocVienDK;
import bean.API.KhoiLopAPI;
import dao.ConnectDataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUntilQLGV;
import utils.DBUtils;

@WebServlet(urlPatterns = { "/api/general/listCourse", "/api/general/courseDetail", "/api/general/listSubject", "/api/general/subject", "/api/general/khoiLop", "/api/general/getTeacher", "/api/general/search"})
public class KhoaHocAPIController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4003951102600400560L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("application/json;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String path = req.getContextPath() + req.getServletPath();
		Connection conn = null;
		try {
			conn = ConnectDataBase.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (path.contains("/api/general/listCourse")) {
			List<KhoaHoc> list = null;
			try {
				list = DBUtils.LayAllKhoaHoc(conn);
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(resp.getOutputStream(), list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (path.contains("/api/general/subject"))
		{
			List<PhanMon> listPM =null;
			try {
				listPM = DBUtils.LayDSPhanMon(conn);
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(resp.getOutputStream(), listPM);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (path.contains("/api/general/listSubject")){
			List<KhoiLopAPI> listKhoiLopAPI = null;
			try {
				listKhoiLopAPI = DBUtils.khoaHocTheoKhoiLop(conn);
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(resp.getOutputStream(), listKhoiLopAPI);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (path.contains("/api/general/khoiLop"))
		{
			List<KhoiLop> khoiLop = null;
			try {
				khoiLop = DBUtils.LayAllKhoiLop(conn);
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(resp.getOutputStream(), khoiLop);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("application/json;charset=UTF-8");
		String path = req.getContextPath() + req.getServletPath();
		Connection conn = null;
		try {
			conn = ConnectDataBase.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (path.contains("/api/general/courseDetail")) {
			String courseId = (String) req.getParameter("courseId");
			ThongKeHocVienDK tkHV = new ThongKeHocVienDK();
			try {
				tkHV = DBUtils.searchById(conn, courseId);
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(resp.getOutputStream(), tkHV);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (path.contains("/api/general/listSubject"))
		{
			String gradeId = (String) req.getParameter("maKhoi");
			List<KhoaHoc> khoaHoc = null;
			try {
				khoaHoc = DBUtils.khoaHocTheoTungKhoiLop(conn, gradeId);
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(resp.getOutputStream(), khoaHoc);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (path.contains("/api/general/getTeacher"))
		{
			String courseId = (String) req.getParameter("courseId");
			GiaoVien gv = new GiaoVien();
			try {
				gv = DBUntilQLGV.findGiaoVienByCourse(conn, courseId);
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(resp.getOutputStream(), gv);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if (path.contains("/api/general/search"))
		{
			String key = (String) req.getParameter("key");
			List<KhoaHoc> khoaHoc = null;
			try {
				khoaHoc = DBUtils.searchByName(conn, key);
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(resp.getOutputStream(), khoaHoc);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
