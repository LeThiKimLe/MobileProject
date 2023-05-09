package servlet.CourseControl.API;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.API.BaiGiangAPI;
import bean.API.VideoBaiHocAPI;
import dao.ConnectDataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUntilQLKH;

@WebServlet(urlPatterns = { "/api/student/getLecture", "/api/student/getDocument" })
public class VideoBaiGiangAPIController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 98686585944591140L;

	@SuppressWarnings("null")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

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
		String path = req.getContextPath() + req.getServletPath();
		String maBaiHoc = req.getParameter("maBaiHoc");
		String maKhoaHoc = req.getParameter("maKhoaHoc");
		
		if (path.contains("/api/student/getLecture")) {
			VideoBaiHocAPI videoBaiHoc = new VideoBaiHocAPI();
			try {
				videoBaiHoc = DBUntilQLKH.VideoBaiGiangHocVien(conn, maBaiHoc, maKhoaHoc);
				if ((videoBaiHoc.getDescription() == null && videoBaiHoc.getVideo() == null) || maBaiHoc == null
						|| maKhoaHoc == null) {
					videoBaiHoc.setResult("fail");
				} else {
					videoBaiHoc.setResult("success");
				}
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(resp.getOutputStream(), videoBaiHoc);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			BaiGiangAPI baiGiang = new BaiGiangAPI();
			try {
				baiGiang = DBUntilQLKH.BaiGiangHocVien(conn, maBaiHoc, maKhoaHoc);
				if(baiGiang.getDocument() == null) {
					baiGiang.setResult("fail");
				}
				else {
					baiGiang.setResult("success");
				}
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(resp.getOutputStream(), baiGiang);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
