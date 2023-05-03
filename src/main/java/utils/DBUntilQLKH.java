package utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bean.*;

import java.math.BigDecimal;

public class DBUntilQLKH {
	public static String pm=null;

	public static List<KhoaHoc> listKhoaHoc(Connection conn) throws SQLException {
        String sql = "select a.MaKhoaHoc,a.TenKhoaHoc,a.MoTa,a.GiaTien,a.SoBaiHoc,a.NgayCapNhat,b.TenPhanMon as PhanMon,c.TenGiaoVien as GiaoVien \r\n"
        		+ "from KhoaHoc as a inner \r\n"
        		+ "join PhanMon as b on a.PhanMon=b.MaPhanMon\r\n"
        		+ "join GiaoVien as c on a.GiaoVien=c.MaGiaoVien";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<KhoaHoc> list = new ArrayList<KhoaHoc>();
        
        
        while (rs.next()) {
            String makh= rs.getString("MaKhoaHoc");
            String tenkh = rs.getString("TenKhoaHoc");
            String mota = rs.getString("MoTa");
            Integer giatien=rs.getInt("GiaTien");           
            Date ngaycn=rs.getDate("NgayCapNhat");
            String phanmonString=rs.getString("PhanMon");
            String giaovienString=rs.getString("GiaoVien");
            
           KhoaHoc kh=new KhoaHoc();
            kh.setMaKhoaHoc(makh);
            kh.setTenKhoaHoc(tenkh);
            kh.setMoTa(mota);
            kh.setGiaTien(giatien);
            kh.setNgayCapNhat(ngaycn);
            kh.setPhanMon(phanmonString);
            kh.setGiaoVien(giaovienString);        
            list.add(kh);
            
        }
        return list;
    }
	public static List<PhanMon> listPhanMon(Connection conn) throws SQLException {
		String sql = "Select MaPhanMon,TenPhanMon from PhanMon";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        
        List<PhanMon> list=new ArrayList<PhanMon>();
        
        while (rs.next()) {
            String maph= rs.getString("MaPhanMon");
            String tenph = rs.getString("TenPhanMon");
            
            
            PhanMon pMon=new PhanMon();
            pMon.setMaPhanMon(maph);
            pMon.setTenPhanMon(tenph);       
            list.add(pMon);
            
        }
        
        return list;
        
	}
	public static List<GiaoVien> listGiaoVien(Connection conn) throws SQLException {
		String sql = "select a.MaGiaoVien,a.TenGiaoVien,b.MaPhanMon from GiaoVien as a inner join ChuyenMon as b on a.MaGiaoVien=b.MaGiaoVien";
        PreparedStatement pstm = conn.prepareStatement(sql);
       
        ResultSet rs = pstm.executeQuery();
        
        List<GiaoVien> list=new ArrayList<GiaoVien>();
        
        while (rs.next()) {
            String magv= rs.getString("MaGiaoVien");
            String tengv = rs.getString("TenGiaoVien");
            String phanmonString=rs.getString("MaPhanMon");
            GiaoVien gv=new GiaoVien();
            gv.setMaGiaoVien(magv);
            gv.setTenGiaoVien(tengv);  
            gv.setChuyenmon(phanmonString);
            list.add(gv);
           
        }
        
        return list;
       
	}
	public static List<GiaoVien> listGVtheoPM(Connection conn) throws SQLException {
		String sql = "select a.MaGiaoVien,a.TenGiaoVien,b.MaPhanMon from GiaoVien as a inner join ChuyenMon as b on a.MaGiaoVien=b.MaGiaoVien where b.MaPhanMon=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        System.out.print("xong+"+pm+"xog");
        pstm.setString(1, pm);
        ResultSet rs = pstm.executeQuery();
        
        List<GiaoVien> list=new ArrayList<GiaoVien>();
        
        while (rs.next()) {
            String magv= rs.getString("MaGiaoVien");
            String tengv = rs.getString("TenGiaoVien");
            String phanmonString=rs.getString("MaPhanMon");
            GiaoVien gv=new GiaoVien();
            gv.setMaGiaoVien(magv);
            gv.setTenGiaoVien(tengv);  
            gv.setChuyenmon(phanmonString);
            list.add(gv);

        }
        
        return list;
       
	}
	
	
	
	
    public static KhoaHoc findKhoaHoc(Connection conn, String inmakh) throws SQLException {
        String sql = "select a.MaKhoaHoc,a.TenKhoaHoc,a.MoTa,a.SoBaiHoc,a.NgayCapNhat,a.GiaTien,a.PhanMon,a.GiaoVien,b.TenPhanMon ,c.TenGiaoVien\r\n"
        		+ "from KhoaHoc as a inner \r\n"
        		+ "join PhanMon as b on a.PhanMon=b.MaPhanMon\r\n"
        		+ "join GiaoVien as c on a.GiaoVien=c.MaGiaoVien\r\n"
        		+ "where a.MaKhoaHoc=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, inmakh);
        
        //"Select a.id, a.name, a.address from Student a where a.id=12";
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	String makh = rs.getString("MaKhoaHoc");
            String tenkh = rs.getString("TenKhoaHoc");  
            String mota = rs.getString("MoTa");
            Integer giatien = rs.getInt("GiaTien");
            String phanmon = rs.getString("PhanMon");
            String giaovienString=rs.getString("GiaoVien");
            Date ngaycapnhat=rs.getDate("NgayCapNhat");
          
            
            KhoaHoc kh=new KhoaHoc(makh, giaovienString, phanmon, tenkh, mota, null, giatien, ngaycapnhat, null);
            pm=phanmon;
            System.out.print(pm);
            return kh;
            
        }
        return null;
    }
	public static void updateKhoaHoc(Connection conn, KhoaHoc kh) throws SQLException {
        String sql = "Update KhoaHoc set TenKhoaHoc=?,MoTa=?,GiaTien=?,NgayCapNhat=?,GiaoVien=? where MaKhoaHoc=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(6, kh.getMaKhoaHoc());
        pstm.setString(1, kh.getTenKhoaHoc());
        pstm.setString(2, kh.getMoTa());
        pstm.setInt(3,kh.getGiaTien());
        pstm.setDate(4,kh.getNgayCapNhat());
         
        pstm.setString(5,kh.getGiaoVien());
        
        pstm.executeUpdate();
        
        
    }
	public static void insertKhoaHoc(Connection conn, KhoaHoc kh) throws SQLException {
        String sql = "Insert KhoaHoc(MaKhoaHoc,TenKhoaHoc,MoTa,GiaTien,NgayCapNhat,PhanMon,GiaoVien) values (?,?,?,?,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, kh.getMaKhoaHoc());
        pstm.setString(2, kh.getTenKhoaHoc());
        pstm.setString(3, kh.getMoTa());
        pstm.setInt(4,kh.getGiaTien());
        pstm.setDate(5,kh.getNgayCapNhat());
        pstm.setString(6,kh.getPhanMon());  
        pstm.setString(7,kh.getGiaoVien());
        pstm.executeUpdate();
  
    }
	
	
}


	

