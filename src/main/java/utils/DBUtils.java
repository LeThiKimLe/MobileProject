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
public class DBUtils 
{
	
	 public static DangNhap YeuCauDangNhap(Connection conn, String username, String password) throws SQLException {
	        String sql = "Select * from DangNhap a where a.username=? and a.password=?";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        
	        pstm.setString(1, username);
	        pstm.setString(2, password);
	    
	        //"Select a.id, a.name, a.address from Student a where a.id=12";
	        ResultSet rs = pstm.executeQuery();
	        String idString=null;
	        while (rs.next()) 
	        {
	        	String role = rs.getString("Role");
	        	if (role.equals("HV"))
	        		idString=rs.getString("MaHocVien");
	        	else if (role.equals("GV"))
	        		idString=rs.getString("MaGiaoVien");
	        	else 
	        		idString=rs.getString("MaQTV");
	        	
	            DangNhap dn = new DangNhap(idString, username, password, role, false);
	            return dn;
	        }
	        return null;
	    }
	 
	 public static void YeuCauDangKy(Connection conn, HocVien hv, DangNhap dn) throws SQLException {
	        String sql = "Insert into HocVien values(?, ?, ?, ?, ?)";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        
	        pstm.setString(1, hv.getMaHocVien());
	        pstm.setString(2, hv.getTenHocVien());
	        pstm.setDate(3, hv.getNgaySinh());
	        pstm.setString(4, hv.getSdt());
	        pstm.setString(5, hv.getEmail());
	        pstm.executeUpdate();
	        
	        String sql2 = "Insert into DangNhap values(?, ?, null, null, ?, ?, null)";
	   	 
	        PreparedStatement pstm2 = conn.prepareStatement(sql2);
	        
	        pstm2.setString(1, dn.getIdString());
	        pstm2.setString(2, hv.getMaHocVien());
	        pstm2.setString(3, dn.getUsername());
	        pstm2.setString(4, dn.getPassword());
	        pstm2.executeUpdate();
	        
	        long millis=System.currentTimeMillis();  
	        java.sql.Date date=new java.sql.Date(millis);  
	        ViThanhToan viThanhToan= new ViThanhToan(conn,hv.getMaHocVien(), 1000000, date);
	        String sql3 = "Insert into ViThanhToan values(?,?,?,?)";
		   	 
	        PreparedStatement pstm3 = conn.prepareStatement(sql3);
	        
	        pstm3.setString(1, viThanhToan.getMaVi());
	        pstm3.setString(2, viThanhToan.getHocVien());
	        pstm3.setBigDecimal(3,BigDecimal.valueOf(viThanhToan.getSoDu()));
	        pstm3.setDate(4, viThanhToan.getNgayCapNhat());
	        pstm3.executeUpdate();
	    }
	 
	 public static void YeuCauDoiMatKhau(Connection conn, String username, String newpass) throws SQLException {
		 String sql = "Update DangNhap set password=? where username=?";
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, newpass);
	        pstm.setString(2, username);
	        pstm.executeUpdate();
	 }
	 
	 public static void ThemBinhLuan(Connection conn, PhanHoi ph) throws SQLException {
		 String sql = "Insert into Rate values(?,?,?,?,?)";
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setInt(1, ph.getId());
	        pstm.setInt(2, ph.getRate());
	        pstm.setString(3, ph.getFeedbackString());
	        pstm.setString(4, ph.getMaHocVienString());
	        pstm.setString(5, ph.getMaKhoaHocString());
	        pstm.executeUpdate();
	 }
	 
	 public static List<PhanHoi> TaiBinhLuan(Connection conn, String maKhoaHoc) throws SQLException {
		 String sql = "Select a.*, b.TenHocVien from Rate a join HocVien b on a.HocVien=b.MaHocVien where a.KhoaHoc=?";
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, maKhoaHoc);
	        ResultSet rs = pstm.executeQuery();
	        
	        List<PhanHoi> list = new ArrayList<PhanHoi>();
		    
	        int id=0;
	        int rate=0;
	        String contentString=null;
	        String maKH=null;
	        String tenHV=null;
	        
	        while (rs.next()) {
	        	
	        	id=rs.getInt("Id");
	        	rate=rs.getInt("Rate");
	        	contentString=rs.getString("Comment");
	        	maKH=rs.getString("KhoaHoc");
	        	tenHV=rs.getString("TenHocVien");
	            PhanHoi mh = new PhanHoi(id, rate, contentString, tenHV, maKH);
	            list.add(mh);
	        }
	        return list;
	 }
	 
	 
	 public static boolean CheckUsername(Connection conn, String username) throws SQLException {
		 	String sql = "Select * from DangNhap where username=?";
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, username);
	        ResultSet rs = pstm.executeQuery();
	        while (rs.next()) 
	        {
	        	return false;
	        }
	        return true;
	 }
	 
	 public static String CheckUseMail(Connection conn, String username) throws SQLException
	 {
		 	String sql = "Select b.* from DangNhap a join HocVien b on a.MaHocVien=b.MaHocVien where username=?";
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, username);
	        ResultSet rs = pstm.executeQuery();
	        
	        if (rs.next())
	        	return rs.getString("Email");
	      
	        String sql2 = "Select b.* from DangNhap a join GiaoVien b on a.MaGiaoVien=b.MaGiaoVien where username=?";
	        PreparedStatement pstm2 = conn.prepareStatement(sql2);
	        pstm2.setString(1, username);
	        ResultSet rs2 = pstm2.executeQuery();
	        if (rs2.next())
	        	return rs2.getString("Email");
	        
	        String sql3 = "Select b.* from DangNhap a join QuanTriVien b on a.MaQTV=b.MaQTV where username=?";
	        PreparedStatement pstm3 = conn.prepareStatement(sql3);
	        pstm3.setString(1, username);
	        ResultSet rs3 = pstm3.executeQuery();
	        if (rs3.next())
	        	return rs3.getString("Email");
	        return null;
	 }
	
	 public static HocVien LayThongTin(Connection conn, String maHocVien) throws SQLException
	 {
		 String sql = "Select * from HocVien a where a.MaHocVien=?";
		 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        
	        pstm.setString(1, maHocVien);
	        //"Select a.id, a.name, a.address from Student a where a.id=12";
	        ResultSet rs = pstm.executeQuery();
	 
	        while (rs.next()) 
	        {
	        	String ten_hocvien = rs.getString("TenHocVien");
	            Date ngay_sinh = rs.getDate("NgaySinh");           
	            String so_dienthoai = rs.getString("SDT");
	            String emailString= rs.getString("Email");
	            HocVien hv = new HocVien(maHocVien,ten_hocvien,ngay_sinh, so_dienthoai, emailString);
	            return hv;
	        }
	        return null;
	 }
	 
	 public static BigDecimal LaySoDu(Connection conn, String maHocVien) throws SQLException
	 {
		 	String sql = "Select SoDu from ViThanhToan a where a.MaHocVien=?";
		 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        
	        pstm.setString(1, maHocVien);
	        
	        //"Select a.id, a.name, a.address from Student a where a.id=12";
	        ResultSet rs = pstm.executeQuery();
	        
	        while (rs.next()) 
	        {
	        	BigDecimal sodu = rs.getBigDecimal("SoDu");
	            return sodu;
	        }
	        return null;
	 }
	 
	 public static List<KhoaHoc> LayAllKhoaHoc(Connection conn) throws SQLException
	 {
		String sql = "select a.*, b.TenGiaoVien, c.TenPhanMon from KhoaHoc a join GiaoVien b on a.GiaoVien=b.MaGiaoVien join PhanMon c on a.PhanMon=c.MaPhanMon";
		 
	    PreparedStatement pstm = conn.prepareStatement(sql);
	   
	    ResultSet rs = pstm.executeQuery();
	 
	    List<KhoaHoc> list = new ArrayList<KhoaHoc>();
	    
        String ma_khoahocString=null;
        String giao_vienString=null;
        String phan_monString=null;
        String ten_khoahocString=null;
        String mo_taString=null;
        int so_baihoc=0;
        int gia_tien=0;
        Date ngay_capnhatDate=null;
        String img=null;
       
        while (rs.next()) {
        	
        	ma_khoahocString=rs.getString("MaKhoaHoc");
        	giao_vienString=rs.getString("TenGiaoVien");
        	phan_monString=rs.getString("TenPhanMon");
        	ten_khoahocString=rs.getString("TenKhoaHoc");
        	mo_taString=rs.getString("MoTa");
        	so_baihoc=rs.getInt("SoBaiHoc");
        	gia_tien=rs.getBigDecimal("GiaTien").intValue();
        	ngay_capnhatDate=rs.getDate("NgayCapNhat");
        	img=rs.getString("HinhAnhMoTa");
        	
            KhoaHoc mh = new KhoaHoc();
            mh.setMaKhoaHoc(ma_khoahocString);
            mh.setGiaoVien(giao_vienString);
            mh.setPhanMon(phan_monString);
            mh.setTenKhoaHoc(ten_khoahocString);
            mh.setMoTa(mo_taString);
            mh.setSoBaiHoc(so_baihoc);
            mh.setGiaTien(gia_tien);
            mh.setNgayCapNhat(ngay_capnhatDate);
            mh.setHinhAnhMoTa(img);
            
            list.add(mh);
        }
        return list;
	 }
	 
	 public static KhoaHoc LayChiTietKhoaHoc(Connection conn, String maKhoaHoc) throws SQLException
	 {
		String sql = "select a.HinhAnhMoTa, a.MoTa, a.GiaTien, a.NgayCapNhat, a.SoBaiHoc, a.TenKhoaHoc from KhoaHoc a where a.MaKhoaHoc=?";
		 
	    PreparedStatement pstm = conn.prepareStatement(sql);
	    
	    pstm.setString(1, maKhoaHoc);
     
        ResultSet rs = pstm.executeQuery();
        
        String ten_khoahocString=null;
        String mo_taString=null;
        int so_baihoc=0;
        int gia_tien=0;
        Date ngay_capnhatDate=null;
        String img=null;
        
        while (rs.next()) 
        {
        	ten_khoahocString=rs.getString("TenKhoaHoc");
        	mo_taString=rs.getString("MoTa");
        	so_baihoc=rs.getInt("SoBaiHoc");
        	gia_tien=rs.getBigDecimal("GiaTien").intValue();
        	ngay_capnhatDate=rs.getDate("NgayCapNhat");
        	img=rs.getString("HinhAnhMoTa");
        	
	    	  KhoaHoc mh = new KhoaHoc();
	    	  mh.setMaKhoaHoc(maKhoaHoc);
	          mh.setTenKhoaHoc(ten_khoahocString);
	          mh.setMoTa(mo_taString);
	          mh.setSoBaiHoc(so_baihoc);
	          mh.setGiaTien(gia_tien);
	          mh.setNgayCapNhat(ngay_capnhatDate);
	          mh.setHinhAnhMoTa(img);
	          
	          return mh;
        }
        return null;
	  
	 }
	 
	 public static GiaoVien LayChiTietGiaoVien(Connection conn, String maKhoaHoc) throws SQLException
	 {
		String sql = "select b.MaGiaoVien, b.TenGiaoVien, b.SDT from KhoaHoc a join GiaoVien b on a.GiaoVien=b.MaGiaoVien where a.MaKhoaHoc=?";
		 
	    PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1, maKhoaHoc);
        ResultSet rs = pstm.executeQuery();
        
        String ma_giaovien=null;
        String ten_giaovien=null;
        String sdt=null;
        
        while (rs.next()) 
        {
        	ma_giaovien=rs.getString("MaGiaoVien");
        	ten_giaovien=rs.getString("TenGiaoVien");
        	sdt=rs.getString("SDT");
        	
	    	  GiaoVien mh = new GiaoVien();
	    	  mh.setMaGiaoVien(ma_giaovien);
	          mh.setTenGiaoVien(ten_giaovien);
	          mh.setSdt(sdt);
	          
	          return mh;
        }
        return null;
	 }
	 
	 public static List<BaiHoc> LayChiTietBaiHoc(Connection conn, String maKhoaHoc) throws SQLException
	 {
		String sql = "select b.* from KhoaHoc a join BaiHoc b on a.maKhoaHoc=b.MaKhoaHoc where a.MaKhoaHoc=?";
		 
	    PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1, maKhoaHoc);
        ResultSet rs = pstm.executeQuery();
        
        String ma_baihoc=null;
        String ten_baihoc=null;
        String mota=null;
        
        List<BaiHoc> bHocs=new ArrayList<BaiHoc>();
        while (rs.next()) 
        {
        	ma_baihoc=rs.getString("MaBaiHoc");
        	ten_baihoc=rs.getString("TenBaiHoc");
        	mota=rs.getString("MoTaNoiDung");
        	
	    	BaiHoc mh = new BaiHoc();
	    	  mh.setMaBaiHoc(ma_baihoc);
	          mh.setTenBaiHoc(ten_baihoc);
	          mh.setMoTaNoiDung(mota);
	          bHocs.add(mh);
        }
        return bHocs;
	 }
	 
		public static int isExisting(String id, List<KhoaHoc> list)
		{
			if(list==null)
				return -1;
			else
			{
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getMaKhoaHoc().equalsIgnoreCase(id)) {
						return i;
					}
				}
				return -1;
			}
		}
	 
		
		public static List<KhoiLop> LayAllKhoiLop(Connection conn) throws SQLException
		 {
			String sql = "select * from KhoiLop";
			 
		    PreparedStatement pstm = conn.prepareStatement(sql);
		   
		    ResultSet rs = pstm.executeQuery();
		 
		    List<KhoiLop> list = new ArrayList<KhoiLop>();
		    
	        String maKhoilop=null;
	        String ten_khoi=null;
	       
	        while (rs.next()) {
	        	
	        	maKhoilop=rs.getString("MaKhoi");
	        	ten_khoi=rs.getString("TenKhoi");
	            KhoiLop kl = new KhoiLop();
	            kl.setMaKhoi(maKhoilop);
	            kl.setTenKhoi(ten_khoi);
	            list.add(kl);
	            System.out.print("Thanh cong");
	        }
	        return list;  
		 }
	 
		public static List<Mon> LayAllMonHoc(Connection conn) throws SQLException
		 {
			String sql = "select * from Mon";
			 
		    PreparedStatement pstm = conn.prepareStatement(sql);
		   
		    ResultSet rs = pstm.executeQuery();
		 
		    List<Mon> list = new ArrayList<Mon>();
		    
	        String maMon=null;
	        String tenMon=null;
	       
	        while (rs.next()) {
	        	
	        	maMon=rs.getString("MaMon");
	        	tenMon=rs.getString("TenMon");
	        	

	            Mon mh = new Mon();
	            mh.setMaMon(maMon);
	            mh.setTenMon(tenMon);
	            
	            list.add(mh);
	            System.out.print("Thanh cong");
	        }
	        return list;
	        
		 }
		 
		 public static List<KhoaHoc> searchByKhoiLop(Connection conn, String tenkhoi) throws SQLException
		 {
			String sql = "select a.GiaoVien, a.GiaTien, a.HinhAnhMoTa, a.MaKhoaHoc, a.MoTa, a.NgayCapNhat, a.PhanMon, \r\n"
					+ "		a.SoBaiHoc, a.TenKhoaHoc, pm.TenPhanMon, kl.TenKhoi, gv.TenGiaoVien\r\n"
					+ "from PhanMon as pm join KhoaHoc as a on a.PhanMon = pm.MaPhanMon join KhoiLop as kl on pm.MaKhoi = kl.MaKhoi join GiaoVien as gv on gv.MaGiaoVien = a.GiaoVien\r\n"
					+ "where TenKhoi = ?";
			 
		    PreparedStatement pstm = conn.prepareStatement(sql);
		    pstm.setString(1, tenkhoi);
	        ResultSet rs = pstm.executeQuery();
	        
	        String tenKHoc=null;
	        String tenGV = null;
	        int giaTien = 0;
	        String img = null;
	        String mota = null;
	        String ma_khoahocString=null;

	        List<KhoaHoc> Khoahoc=new ArrayList<KhoaHoc>();
	        while (rs.next()) 
	        {
	        	ma_khoahocString=rs.getString("MaKhoaHoc");
	        	tenKHoc=rs.getString("TenKhoaHoc");
	        	tenGV=rs.getString("TenGiaoVien");
	        	giaTien=rs.getBigDecimal("GiaTien").intValue();
	        	img = rs.getString("HinhAnhMoTa");
	        	mota = rs.getString("MoTa");
	        
		    	 KhoaHoc kh = new KhoaHoc();
		    	 kh.setMaKhoaHoc(ma_khoahocString);
		    	 kh.setGiaoVien(tenGV);
		    	 kh.setTenKhoaHoc(tenKHoc);
		    	 kh.setGiaTien(giaTien);
		    	 kh.setHinhAnhMoTa(img);
		    	 kh.setMoTa(mota);
		         
		    	 Khoahoc.add(kh);
		         System.out.print("Thanh cong1");
	        }
	        //System.out.print(Khoahoc);
	        return Khoahoc;	  
		 }
		 
		 public static List<KhoaHoc> searchByName(Connection conn, String name) throws SQLException
		 {
			String sql = "select a.GiaoVien, a.GiaTien, a.HinhAnhMoTa, a.MaKhoaHoc, a.MoTa, a.NgayCapNhat, a.PhanMon, a.SoBaiHoc, a.TenKhoaHoc, gv.TenGiaoVien \r\n"
					+ "from KhoaHoc as a\r\n"
					+ "inner join GiaoVien as gv on a.GiaoVien = gv.MaGiaoVien\r\n"
					+ "where a.TenKhoaHoc like ?";
			 
		    PreparedStatement pstm = conn.prepareStatement(sql);
		    pstm.setString(1, "%"+name+"%");
	        ResultSet rs = pstm.executeQuery();
	        
	        String tenKHoc=null;
	        String tenGV = null;
	        int giaTien = 0;
	        String img = null;
	        String mota = null;
	        String ma_khoahocString=null;

	        List<KhoaHoc> tenKH = new ArrayList<KhoaHoc>();
	        while(rs.next()) 
	        {
	        	ma_khoahocString=rs.getString("MaKhoaHoc");
	        	tenKHoc=rs.getString("TenKhoaHoc");
	        	tenGV=rs.getString("TenGiaoVien");
	        	giaTien=rs.getBigDecimal("GiaTien").intValue();
	        	img = rs.getString("HinhAnhMoTa");
	        	mota = rs.getString("MoTa");
	        
		    	 KhoaHoc kh = new KhoaHoc();
		    	 kh.setMaKhoaHoc(ma_khoahocString);
		    	 kh.setGiaoVien(tenGV);
		    	 kh.setTenKhoaHoc(tenKHoc);
		    	 kh.setGiaTien(giaTien);
		    	 kh.setHinhAnhMoTa(img);
		    	 kh.setMoTa(mota);
		         
		    	 tenKH.add(kh);
		         System.out.print("Thanh cong1");
	        }
	        System.out.print(tenKH);
	        return tenKH;	  
		 }
		 
		 public static String getAlphaNumericString(int n)
		 {
		 
		  // chose a Character random from this String
		  String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		         + "0123456789"
		         + "abcdefghijklmnopqrstuvxyz";
		 
		  // create StringBuffer size of AlphaNumericString
		  StringBuilder sb = new StringBuilder(n);
		 
		  for (int i = 0; i < n; i++) {
		 
		   // generate a random number between
		   // 0 to AlphaNumericString variable length
		   int index
		    = (int)(AlphaNumericString.length()
		      * Math.random());
		 
		   // add Character one by one in end of sb
		   sb.append(AlphaNumericString
		      .charAt(index));
		  }
		 
		  return sb.toString();
		 }
	
}
