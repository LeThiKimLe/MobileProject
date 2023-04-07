package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ConnectDataBase;
import utils.DBUtils;

public class PhanHoi {
	private int id;
	private int rate;
	private String feedbackString;
	private String maHocVienString;
	private String maKhoaHocString;
	private String tenHocVienString;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getFeedbackString() {
		return feedbackString;
	}
	public void setFeedbackString(String feedbackString) {
		this.feedbackString = feedbackString;
	}
	public String getMaHocVienString() {
		return maHocVienString;
	}
	public void setMaHocVienString(String maHocVienString) {
		this.maHocVienString = maHocVienString;
	}
	public String getMaKhoaHocString() {
		return maKhoaHocString;
	}
	public void setMaKhoaHocString(String maKhoaHocString) {
		this.maKhoaHocString = maKhoaHocString;
	}
	
	public int autoID(Connection conn) throws SQLException
	{
		
		String sql = "SELECT COUNT(*) as SoLuong FROM Rate";
		
		PreparedStatement pstm=null;
		try {
			pstm = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) 
        {
        	int soluong = rs.getInt("SoLuong");
        	return soluong+1;
        }
        return 0;  
	}
	
	public PhanHoi(Connection conn,int rate, String content, String hv, String kh) throws SQLException
	{
		this.id=autoID(conn);
		this.rate=rate;
		this.feedbackString=content;
		this.maHocVienString=hv;
		this.maKhoaHocString=kh;
		DBUtils.ThemBinhLuan(conn, this);
	}
	
	public PhanHoi(int id,int rate, String content, String hv, String kh) throws SQLException
	{
		this.id=id;
		this.rate=rate;
		this.feedbackString=content;
		this.tenHocVienString=hv;
		this.maKhoaHocString=kh;
	}
	public String getTenHocVienString() {
		return tenHocVienString;
	}
	public void setTenHocVienString(String tenHocVienString) {
		this.tenHocVienString = tenHocVienString;
	}
	
	
	

}
