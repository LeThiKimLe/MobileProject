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
import bean.KhoaHoc;
import bean.API.BaiGiangAPI;
import bean.API.KhoaHocAPI;
import bean.API.TeacherAPI;
import bean.API.VideoBaiHocAPI;
import dao.ConnectDataBase;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUntilQLGV;
import utils.DBUntilQLKH;
import utils.DBUtils;

@WebServlet(urlPatterns = { "/api/manager/setCourse" })
public class CourseAPIController extends HttpServlet{

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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		String action = request.getParameter("action");
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String phanmonString=new String(request.getParameter("phanMon").getBytes("UTF-8"));
        String tenkh = new String(request.getParameter("tenKhoaHoc").getBytes( "UTF-8"));
        String mota = new String(request.getParameter("moTa").getBytes("UTF-8"));
        String giatien=new String(request.getParameter("giaTien").getBytes("UTF-8"));
        String giaovienString =new String(request.getParameter("giaoVien").getBytes("UTF-8"));
        String ngaycapnhatString = (String) request.getParameter("ngayCapNhat") ;  
        java.sql.Date ngaykkDate=null;
        java.util.Date ngaykk=null;
		try {
			ngaykk = format.parse(ngaycapnhatString);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int gia=0;
        try {
        	ngaykkDate = new java.sql.Date( ngaykk.getTime() );
        	gia=Integer.parseInt(giatien);
        }
        catch (Exception e) {
        	
        }
		
		KhoaHocAPI khoaHocResponse = new KhoaHocAPI();
		if (action.equals("add"))
		{
	        KhoaHoc kh=null;
			try {
				kh = new KhoaHoc(giaovienString, phanmonString, tenkh, mota, null, gia, ngaykkDate, null);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
 
	       try {
	            DBUntilQLKH.insertKhoaHoc(conn, kh);
	            khoaHocResponse.setResult("success");
	            khoaHocResponse.setMessage("Thêm khóa học thành công");
	            khoaHocResponse.setMaKH(kh.getMaKhoaHoc());
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
		else if (action.equals("update"))
		{
			String makh= (String)request.getParameter("maKhoaHoc");
			KhoaHoc kh=null;
			try {
				kh = new KhoaHoc(makh, giaovienString, phanmonString, tenkh, mota, null, gia, ngaykkDate, null);
				DBUntilQLKH.updateKhoaHoc(conn, kh);
				khoaHocResponse.setResult("success");
	            khoaHocResponse.setMessage("Đã sửa khóa học thành công");
	            khoaHocResponse.setMaKH(kh.getMaKhoaHoc());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try {
	
			ObjectMapper obj = new ObjectMapper();
			conn.close();
			obj.writeValue(response.getOutputStream(), khoaHocResponse);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
