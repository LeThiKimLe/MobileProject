package servlet.TeacherController.API;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.BaiHoc;
import bean.HocVien;
import bean.API.ThongKeHocVienAPI;
import dao.ConnectDataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUntilQLGV;
import utils.DBUtilsGiaoVien;

@WebServlet(urlPatterns = {"/api/teacher/setLesson"})
public class BaiHocAPIController extends HttpServlet {

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
		
		String action = req.getParameter("action");
		String maGiaoVien = req.getParameter("maGiaoVien"); //mã giảng viên
		String maKhoaHoc = req.getParameter("maKhoaHoc");
		
		boolean checkGiaoVien=true;
		try {
			checkGiaoVien = DBUtilsGiaoVien.checkKhoaHocGiaoVien(conn, maKhoaHoc, maGiaoVien);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (checkGiaoVien)
		{
			String maBaiHoc= req.getParameter("maBaiHoc");
			String tieuDe = req.getParameter("tenBaiHoc");
			String moTa = req.getParameter("moTaNoiDung");
			BaiHoc editedLesson = null;
			if (action.equals("add"))
			{
				try {
					editedLesson= new BaiHoc(conn, maKhoaHoc, tieuDe, moTa);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (action.equals("delete"))
			{
				
				try {
					DBUtilsGiaoVien.XoaBaiHoc(conn, maBaiHoc);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else if (action.equals("modify"))
			{
				editedLesson = new BaiHoc(maBaiHoc, maKhoaHoc, tieuDe, moTa);
				try {
					DBUtilsGiaoVien.SuaBaiHoc(conn, editedLesson);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(resp.getOutputStream(), editedLesson);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
}
