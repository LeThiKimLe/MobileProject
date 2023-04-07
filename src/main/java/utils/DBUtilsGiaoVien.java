package utils;
import java.math.BigDecimal;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import bean.*;

public class DBUtilsGiaoVien {
	
	public static void SuaBaiHoc(Connection conn, BaiHoc editBaiHoc) throws SQLException
	 {
		String maBaiHocString= editBaiHoc.getMaBaiHoc();
		String tenBaiHocString=editBaiHoc.getTenBaiHoc();
		String motaBaiHocString=editBaiHoc.getMoTaNoiDung();
		String sql = "Update BaiHoc set TenBaiHoc=?, MoTaNoiDung=? where BaiHoc.MaBaiHoc=?";
		 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        
	        pstm.setString(1, tenBaiHocString);
	        pstm.setString(2, motaBaiHocString);
	        pstm.setString(3, maBaiHocString);
	        pstm.executeUpdate();
	 }
	
	public static void editBaiGiang(Connection conn, String maBaiHoc, String link) throws SQLException
	 {
		 String formatString= link.replace("watch?v=","embed/"); 
		 String sql = "Update TaiNguyen set BaiGiang=? where MaBaiHoc=?";
		 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, formatString);
	        pstm.setString(2, maBaiHoc);
	        //"Select a.id, a.name, a.address from Student a where a.id=12";
	        pstm.executeUpdate();
	 }
	
	public static void editLyThuyet(Connection conn, String maBaiHoc, String lyThuyet) throws SQLException
	 {
		 	String sql = "Update TaiNguyen set LyThuyet=? where MaBaiHoc=?";
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, lyThuyet);
	        pstm.setString(2, maBaiHoc);
	        //"Select a.id, a.name, a.address from Student a where a.id=12";
	        pstm.executeUpdate();
	 }
	
	public static void editBaiTap(Connection conn, String maBaiHoc, String baiTap) throws SQLException
	 {
		 	String sql = "Update TaiNguyen set BaiTap=? where MaBaiHoc=?";
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, baiTap);
	        pstm.setString(2, maBaiHoc);
	        //"Select a.id, a.name, a.address from Student a where a.id=12";
	        pstm.executeUpdate();
	 }
	
	public static void ThemBaiHoc(Connection conn, BaiHoc editBaiHoc) throws SQLException
	 {
		String maBaiHocString= editBaiHoc.getMaBaiHoc();
		String maKhoaHocString=editBaiHoc.getKhoaHoc();
		String tenBaiHocString=editBaiHoc.getTenBaiHoc();
		String motaBaiHocString=editBaiHoc.getMoTaNoiDung();
		
		 String sql = "Insert into BaiHoc values (?,?,?,?)";
		 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        
	        pstm.setString(1, maBaiHocString);
	        pstm.setString(2, tenBaiHocString);
	        pstm.setString(3, motaBaiHocString);
	        pstm.setString(4, maKhoaHocString);
	        pstm.executeUpdate();
	        
        String sql2 = "Insert into TaiNguyen values (?,null,null,null)";
        PreparedStatement pstm2 = conn.prepareStatement(sql2);
        pstm2.setString(1, maBaiHocString);
        pstm2.executeUpdate();
	        
	      
	 }
	
	public static void XoaBaiHoc(Connection conn, String maBaiHoc) throws SQLException
	 {
		String maBaiHocString= maBaiHoc;
		
		String sql = "Delete from BinhLuan where MaBaiHoc=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, maBaiHocString);
        pstm.executeUpdate();
		
        String sql2 = "Delete from TaiNguyen where MaBaiHoc=?";
        PreparedStatement pstm2 = conn.prepareStatement(sql2);
        pstm2.setString(1, maBaiHocString);
        pstm2.executeUpdate();
		
        
		String sql3 = "Delete from BaiHoc where MaBaiHoc=?";
        PreparedStatement pstm3 = conn.prepareStatement(sql3);
        pstm3.setString(1, maBaiHocString);
        pstm3.executeUpdate();
	 }
	
	
	
	
	
	
	
}
