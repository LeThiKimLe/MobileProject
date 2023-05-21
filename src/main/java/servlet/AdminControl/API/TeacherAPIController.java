package servlet.AdminControl.API;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.DonHang;
import bean.GiaoVien;
import bean.API.BaiGiangAPI;
import bean.API.TeacherAPI;
import bean.API.VideoBaiHocAPI;
import dao.ConnectDataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUntilQLGV;
import utils.DBUntilQLKH;
import utils.DBUtils;

@WebServlet(urlPatterns = { "/api/manager/getTeacher", "/api/manager/setTeacher", "/api/manager/getTeacherByPhanMon" })
public class TeacherAPIController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -779693565538978343L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		try {
			List<GiaoVien> listGiaoVien = DBUntilQLGV.listThongTinGiaoVien(conn);
			ObjectMapper obj = new ObjectMapper();
			conn.close();
			obj.writeValue(resp.getOutputStream(), listGiaoVien);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
		String action = req.getParameter("action");
		//String maKhoaHoc = req.getParameter("maKhoaHoc");
		
		if (path.contains("/api/manager/setTeacher")) {
			
			TeacherAPI teacherResp = new TeacherAPI();
			
			if (action.equals("add"))
			{
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		        String tengv = new String(req.getParameter("tenGiaoVien").getBytes("UTF-8"));
		        String sdt = new String(req.getParameter("sdt").getBytes( "UTF-8"));
		        String email = new String(req.getParameter("email").getBytes( "UTF-8"));
		        String cccdString=new String(req.getParameter("cccd").getBytes("UTF-8"));
		        String diachi=new String(req.getParameter("diaChi").getBytes( "UTF-8"));
		        String ngaykyketString = (String) req.getParameter("ngayKyKet");
		        String chuyenString=new String(req.getParameter("chuyenmon").getBytes("UTF-8"));
		        
		        java.sql.Date ngaykkDate=null;
		        java.util.Date ngaykk=null;
				try {
					ngaykk = format.parse(ngaykyketString);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        try {
		        	ngaykkDate = new java.sql.Date(ngaykk.getTime() );
		        }
		        catch (Exception e) {	
		        	e.printStackTrace();
		        }
		        GiaoVien gVien;
				try {
					gVien = new GiaoVien(tengv, sdt, email ,cccdString, diachi, ngaykkDate, chuyenString, true);
					DBUntilQLGV.insertGiaoVienAPI(conn, gVien);
					teacherResp.setResult("success");
					teacherResp.setMaGV(gVien.getMaGiaoVien());
					teacherResp.setMessage("Đã thêm giáo viên thành công");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if (action.equals("delete"))
			{
		       String idStr = (String) req.getParameter("maGiaoVien");
		       GiaoVien gv = null;
		        try {
		            DBUntilQLGV.deleteGiaoVien(conn, idStr);
		            teacherResp.setResult("success");
					teacherResp.setMessage("Đã xóa giáo viên thành công");
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			}
			try {
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(resp.getOutputStream(),teacherResp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (path.contains("/api/manager/getTeacherByPhanMon"))
		{
			String maPhanMon=new String(req.getParameter("maPhanMon").getBytes( "UTF-8"));
			List<GiaoVien> listGV= null;
			try {
				listGV = DBUntilQLKH.listGVtheoPMAPI(conn, maPhanMon);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(resp.getOutputStream(),listGV);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
