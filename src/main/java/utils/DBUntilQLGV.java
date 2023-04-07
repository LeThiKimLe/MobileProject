package utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bean.*;
import bean.GiaoVien;

public class DBUntilQLGV {
	public static List<GiaoVien> listGiaoVien(Connection conn) throws SQLException {
        String sql = "Select MaGiaoVien,TenGiaoVien,SDT,CCCD,DiaChi,NgayKyKet from GiaoVien where TrangThaiLamViec=1";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        
        List<GiaoVien> list = new ArrayList<GiaoVien>();
        
        while (rs.next()) {
            String magv= rs.getString("MaGiaoVien");
            String tenGV = rs.getString("TenGiaoVien");
            String sdt = rs.getString("SDT");
            String cccd = rs.getString("CCCD");
            String diachi = rs.getString("DiaChi");
            Date ngaykyket=rs.getDate("NgayKyKet");
            
            GiaoVien gv = new GiaoVien();
            gv.setMaGiaoVien(magv);
            gv.setTenGiaoVien(tenGV);
            gv.setSdt(sdt); 
            gv.setCccd(cccd);
            gv.setDiaChi(diachi);
            gv.setNgayKyKet(ngaykyket);
            list.add(gv);
            
        }
        return list;
    }
    public static GiaoVien findGiaoVien(Connection conn, String inmagv) throws SQLException {
        String sql = "Select MaGiaoVien,TenGiaoVien,SDT,CCCD,DiaChi,NgayKyKet from GiaoVien a where a.MaGiaoVien=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, inmagv);
        
        //"Select a.id, a.name, a.address from Student a where a.id=12";
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	String magv = rs.getString("MaGiaoVien");
            String tengv = rs.getString("TenGiaoVien");  
            String sdt = rs.getString("SDT");
            String cccd = rs.getString("CCCD");
            String diachi = rs.getString("DiaChi");
            Date ngaykyket=rs.getDate("NgayKyKet");
            GiaoVien gVien = new GiaoVien(magv ,tengv, sdt, cccd, diachi, ngaykyket, null);   
            return gVien;
        }
        return null;
    }
	public static void updateGiaoVien(Connection conn, GiaoVien gv) throws SQLException {
        String sql = "Update GiaoVien set TenGiaoVien=?, SDT=?,CCCD=?,DiaChi=?,NgayKyKet=? where MaGiaoVien=? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, gv.getTenGiaoVien());
        pstm.setString(2, gv.getSdt());
        pstm.setString(3, gv.getCccd());
        pstm.setString(4,gv.getDiaChi());
        pstm.setDate(5,gv.getNgayKyKet() );
        pstm.setString(6, gv.getMaGiaoVien());      
        pstm.executeUpdate();
    
    }
	public static void insertGiaoVien(Connection conn, GiaoVien gv) throws SQLException {
        String sql = "Insert GiaoVien values (?, ?, ?, ?, ?, ?,1, ?)";
        String sql1="Insert ChuyenMon values(?,?)";
        String sql2="Insert into DangNhap(id,MaGiaoVien,Username,Password,Role) values(?,?,?,?,?)";
        
 //
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, gv.getMaGiaoVien());
        pstm.setString(2, gv.getTenGiaoVien());
        pstm.setString(3, gv.getSdt());
        pstm.setString(4, gv.getCccd());
        pstm.setString(5,gv.getDiaChi());
        pstm.setDate(6,gv.getNgayKyKet() ); 
        pstm.setString(7,gv.getEmail()); 
        pstm.executeUpdate();
        
        PreparedStatement pstm1 = conn.prepareStatement(sql1);
        pstm1.setString(1, gv.getMaGiaoVien());
        pstm1.setString(2, gv.getChuyenmon());
        pstm1.executeUpdate();
        
        DangNhap dNhap=new DangNhap();
        PreparedStatement pstm2 = conn.prepareStatement(sql2);
        pstm2.setString(1, dNhap.getId());
        pstm2.setString(2, gv.getMaGiaoVien());
        pstm2.setString(3, gv.getSdt());
        pstm2.setString(4, "1234");
        pstm2.setString(5, "GV");
        pstm2.executeUpdate();
    }
	public static void deleteGiaoVien(Connection conn, String inmagv) throws SQLException {
        String sql = "Update GiaoVien set TrangThaiLamViec=0 where MaGiaoVien=?";
        String sql1 = "Delete from ChuyenMon where MaGiaoVien=?";
        String sql2 = "Delete from DangNhap where MaGiaoVien=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, inmagv);  
        pstm.executeUpdate();
        PreparedStatement pstm1 = conn.prepareStatement(sql1);
        pstm1.setString(1, inmagv); 
        pstm1.executeUpdate();
        PreparedStatement pstm2 = conn.prepareStatement(sql2);
        pstm2.setString(1, inmagv); 
        pstm2.executeUpdate();
        
    }
}


	

