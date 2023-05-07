package servlet.CourseControl.API;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.API.KhoaHocHocVienAPI;
import dao.ConnectDataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUntilQLKH;

@WebServlet(urlPatterns = { "/api/student/myCourse" })
public class KhoaHocHocVienAPIController extends HttpServlet {

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
		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	}
}
