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
		System.out.print(maBaiHocString+ tenBaiHocString+ motaBaiHocString);
		 String sql = "Update BaiHoc set TenBaiHoc=?, MoTaNoiDung=? where BaiHoc.MaBaiHoc=?";
		 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        
	        pstm.setString(1, tenBaiHocString);
	        pstm.setString(2, motaBaiHocString);
	        pstm.setString(3, maBaiHocString);
	       
	        //"Select a.id, a.name, a.address from Student a where a.id=12";
	        pstm.executeUpdate();
	 }
	
	public static void ThemBaiHoc(Connection conn, BaiHoc editBaiHoc) throws SQLException
	 {
		String maBaiHocString= editBaiHoc.getMaBaiHoc();
		String maKhoaHocString=editBaiHoc.getMaKhoaHoc();
		String tenBaiHocString=editBaiHoc.getTenBaiHoc();
		String motaBaiHocString=editBaiHoc.getMoTaNoiDung();
		
		 String sql = "Insert into BaiHoc values (?,?,?,?)";
		 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        
	        pstm.setString(1, maBaiHocString);
	        pstm.setString(2, tenBaiHocString);
	        pstm.setString(3, motaBaiHocString);
	        pstm.setString(4, maKhoaHocString);
	       
	        //"Select a.id, a.name, a.address from Student a where a.id=12";
	        pstm.executeUpdate();
	 }
	
	public static void XoaBaiHoc(Connection conn, String maBaiHoc) throws SQLException
	 {
		
		String sql1 = "Delete from TaiNguyen where MaBaiHoc=?";
	 
        PreparedStatement pstm = conn.prepareStatement(sql1);
        
        pstm.setString(1, maBaiHoc);
        
        pstm.executeUpdate();
        
        String sql2 = "Delete from BaiHoc where MaBaiHoc=?";
        
        pstm = conn.prepareStatement(sql2);
        
        pstm.setString(1, maBaiHoc);
        
        pstm.executeUpdate();
        
	 }
	
	public static boolean checkKhoaHocGiaoVien(Connection conn, String maKhoaHoc, String maGiaoVien) throws SQLException
	{
		String sql = "Select * from KhoaHoc where GiaoVien=? and MaKhoaHoc=?";
		PreparedStatement ptsm = conn.prepareStatement(sql);
		ptsm.setString(1, maGiaoVien);
		ptsm.setString(2, maKhoaHoc);
		ResultSet rs = ptsm.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
		
		
	}
	
	
	
	
	
	
	
}
