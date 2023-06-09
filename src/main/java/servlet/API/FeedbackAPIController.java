package servlet.API;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.PhanHoi;
import bean.API.FeedbackAPI;
import dao.ConnectDataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUntilQLKH;
import utils.DBUtils;

@WebServlet(urlPatterns = { "/api/general/getFeedback","/api/general/sendFeedback"})
public class FeedbackAPIController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 356186268164869556L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("application/json;charset=UTF-8");
		String path = req.getContextPath() + req.getServletPath();
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
		
		String maKhoaHoc = req.getParameter("maKhoaHoc");
		if (path.contains("/api/general/getFeedback")) { 
			try {
				List<FeedbackAPI> listFeedback = DBUtils.LayDanhSachFeedBack(conn, maKhoaHoc);
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(resp.getOutputStream(), listFeedback);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (path.contains("/api/general/sendFeedback")) 
		{
			String maHocVien = req.getParameter("maHocVien");
			int rate = Integer.parseInt(req.getParameter("rate"));
			String content = req.getParameter("content");
			PhanHoi new_rate=null;
			
			boolean checkDaDangKy=true;
			
			try {
				checkDaDangKy = DBUntilQLKH.CheckDaDangKy(conn, maHocVien, maKhoaHoc);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (checkDaDangKy)
			{
				try {
					new_rate= new PhanHoi(conn, rate , content, maHocVien, maKhoaHoc);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(resp.getOutputStream(), new_rate);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
