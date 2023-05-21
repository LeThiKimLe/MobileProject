package servlet.AdminControl.API;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.API.MessageConfirmOrderAPI;
import dao.ConnectDataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtils;

@WebServlet(urlPatterns = { "/api/manager/updateOrderUnconfirmed" })
public class CapNhatDanhSachDonHangChuaXacNhanAPIController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9092455370742369650L;

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
		String maDonHang = req.getParameter("maDonHang");
		String xacNhan = req.getParameter("xacNhan");
		ObjectMapper obj = new ObjectMapper();
		MessageConfirmOrderAPI message = new MessageConfirmOrderAPI();
		try {
			if (DBUtils.CheckDonHang(conn, maDonHang)) {
				if (xacNhan.trim().equals("1") || xacNhan.toLowerCase().trim().equals("true")) {
					DBUtils.XacNhanDonHang(conn, maDonHang);
					message.setResult("success");
					message.setMessage("Xác nhận đơn hàng thành công");
				} else if (xacNhan.trim().equals("0") || xacNhan.toLowerCase().trim().equals("false")) {
					DBUtils.HoanXacNhanDonHang(conn, maDonHang);
					message.setResult("success");
					message.setMessage("Hoãn xác nhận đơn hàng thành công");
				} else {
					message.setResult("fail");
					message.setMessage("Mã xác nhận không tồn tại");
				}
			}else {
				message.setResult("fail");
				message.setMessage("Mã đơn hàng không tồn tại");
			}
			conn.close();
			obj.writeValue(resp.getOutputStream(), message);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
