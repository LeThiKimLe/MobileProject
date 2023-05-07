package servlet.CourseControl.API;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.KhoaHoc;
import bean.KhoiLop;
import bean.API.KhoiLopAPI;
import dao.ConnectDataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtils;

@WebServlet(urlPatterns = { "/api/general/listCourse", "/api/general/listSubject" })
public class KhoaHocAPIController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4003951102600400560L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		System.out.println(path);
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
		else {
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
	}
}