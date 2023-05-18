package servlet.API;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.DangNhap;
import bean.DonHang;
import bean.HocVien;
import bean.KhoaHoc;
import bean.API.DangKyKHMessageAPI;
import bean.API.DonHangAPI;
import bean.API.HocVienAPI;
import bean.API.SoDuAPI;
import dao.ConnectDataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUntilQLKH;
import utils.DBUtils;

@WebServlet(urlPatterns = {"/api/student/getBalance", "/api/student/regisRequest", "/api/student/payBill", "/api/student/getBill"})
public class ThanhToanAPIController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("application/json;charset=UTF-8");
		String path = request.getContextPath() + request.getServletPath();
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
		String maHocVien = (String) request.getParameter("maHocVien");
		
		if (path.contains("/api/student/getBalance")) {
		
			HocVienAPI hvApi = new HocVienAPI();
			String checkUser = null;
			SoDuAPI new_budget= new SoDuAPI();
			try {
				checkUser = DBUtils.KiemTraUser(conn, maHocVien);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if (checkUser == null) {
				new_budget.setResult("fail");
				new_budget.setMessage("Tài khoản không tồn tại");
			} else 
			{	
				try {
					new_budget= DBUtils.LaySoDuVi(conn, maHocVien);
					new_budget.setResult("success");
					new_budget.setMessage("Lấy số dư thành công");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(response.getOutputStream(), new_budget);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (path.contains("/api/student/getBill")) {
			String maHoaDon = (String) request.getParameter("maHoaDon");
			DonHangAPI bill = new DonHangAPI();
			int kqCheck=0;
			try {
				kqCheck= DBUtils.checkHoaDon(conn, maHoaDon, maHocVien);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (kqCheck==0)
			{
				bill.setResult("fail");
				bill.setMessage("Hóa đơn không tồn tại hoặc đã bị hủy");
			}
			else if (kqCheck==-1)
			{
				bill.setResult("fail");
				bill.setMessage("Bạn không có quyền xem đơn hàng này");
			}
			else {
				try {
					bill = DBUtils.getBillInfor(conn, maHoaDon);
					bill.setResult("success");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(response.getOutputStream(), bill);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			Boolean checkHocVien = true;
			DangKyKHMessageAPI dk = new DangKyKHMessageAPI();
			try {
				checkHocVien = DBUntilQLKH.CheckHocVien(conn, maHocVien);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!checkHocVien) {
				dk.setResult("fail");
				dk.setMessage("Học viên không tồn tại");
			}
			else 
			{
			
			if (path.contains("/api/student/regisRequest"))
			{
				String cacKhoaHoc = request.getParameter("cacKhoaHoc");
				List<String> listKhoaHocDK = new ArrayList<String>(
						Arrays.asList(cacKhoaHoc.replaceAll("\\s|\\[|\\]", "").split(",")));
	
				Boolean checkKhoaHoc = true;
				Boolean checkDaDangKy = false;
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
				if (checkHocVien && checkKhoaHoc && !checkDaDangKy)
				{
					 String maDon="";
					 long millis = System.currentTimeMillis();
					 java.sql.Date today = new java.sql.Date(millis);
					DonHang donHang=null;
						try {
							donHang = new DonHang(conn, today, maHocVien);
							maDon=donHang.getMaDonHang();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					for (String maKhoaHoc : listKhoaHocDK) {
						try {
							donHang.addItem(conn, maKhoaHoc);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					try {
						donHang.HoanTatTongHoaDon(conn);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					dk.setResult("success");
					dk.setMessage("Đã gửi yêu cầu thành công");
					dk.setMaHoaDon(maDon);
				}
			}
			else if (path.contains("/api/student/payBill")) 
			{
				String maHoaDon = request.getParameter("maHoaDon");
				int kqCheck=0;
				try {
					kqCheck= DBUtils.checkHoaDon(conn, maHoaDon, maHocVien);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (kqCheck==0)
				{
					dk.setResult("fail");
					dk.setMessage("Hóa đơn không tồn tại hoặc đã bị hủy");
				}
				else if (kqCheck==-1)
				{
					dk.setResult("fail");
					dk.setMessage("Bạn không có quyền thanh toán đơn hàng này");
				}
				else if (kqCheck==1)
				{
					dk.setResult("fail");
					dk.setMessage("Bạn đã thanh đoán đơn này rồi");
				}
				
				else if (kqCheck==3)
				{
					dk.setResult("fail");
					dk.setMessage("Quản trị viên vẫn chưa duyệt đơn hàng của bạn. Hãy chờ nhé");
				}
				else if (kqCheck==4)
				{
					dk.setResult("fail");
					dk.setMessage("Đơn hàng của bạn bị quản trị viên từ chối");
				}
				else if (kqCheck==2)
				{
					boolean checkDaDangKy=false;
					try {
						DBUtils.ThanhToanHoaDon(conn, maHoaDon, maHocVien);
						List<String> listKH= DBUtils.LayDSDaDK(conn, maHoaDon);
						
						for (String string : listKH) {
							try {
								checkDaDangKy = DBUntilQLKH.CheckDaDangKy(conn, maHocVien, string);
								if (checkDaDangKy) {
									dk.setResult("fail");
									dk.setMessage("Khóa học " + string + " học viên đã đăng ký");
									break;
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (checkDaDangKy==false)
						{
							for (String string : listKH) 
							{
								try {
									DBUntilQLKH.DangKy(conn, maHocVien, string);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							dk.setMaHoaDon(maHoaDon);
							dk.setResult("success");
							dk.setMessage("Bạn đã đăng ký khóa học thành công");
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			}
			try {
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(response.getOutputStream(), dk);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
