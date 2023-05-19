package utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bean.*;
import bean.API.KhoaHocGiaoVienAPI;
import bean.API.SoLuongHocVienDangKyKhoaHoc;

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
    public static Boolean checkMaGiaoVien(Connection conn, String inmagv) throws SQLException {
        String sql = "Select MaGiaoVien,TenGiaoVien,SDT,CCCD,DiaChi,NgayKyKet from GiaoVien a where a.MaGiaoVien=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, inmagv);
        
        //"Select a.id, a.name, a.address from Student a where a.id=12";
        ResultSet rs = pstm.executeQuery();
 
        if(rs.next())
        	return true;
        return false;
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
	/**
	 * Danh sách các khóa học của giáo viên 
	 * @param conn
	 * @param maGiaoVien
	 * @return
	 * @throws SQLException
	 */
	public static List<KhoaHocGiaoVienAPI> DanhSachKhoaHocGiaoVien(Connection conn, String maGiaoVien)
			throws SQLException {
		String sql = "select kh.*, pm.TenPhanMon \r\n"
				+ "from KhoaHoc as kh\r\n"
				+ "inner join PhanMon as pm on kh.PhanMon = pm.MaPhanMon\r\n"
				+ "where kh.GiaoVien = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maGiaoVien);
		ResultSet rs = pstm.executeQuery();
		List<KhoaHocGiaoVienAPI> listKhGV = new ArrayList<KhoaHocGiaoVienAPI>();
		while(rs.next()) {
			KhoaHocGiaoVienAPI kh = new KhoaHocGiaoVienAPI();
			kh.setMaKhoaHoc(rs.getString("MaKhoaHoc"));
			kh.setTenKhoaHoc(rs.getNString("TenKhoaHoc"));
			kh.setPhanMon(rs.getNString("TenPhanMon"));
			kh.setHinhAnhMoTa(rs.getString("HinhAnhMoTa"));
			listKhGV.add(kh);
		}
		return listKhGV;
	}

	public static Integer SoLuongHocVienDangKyGiaoVien(Connection conn, String maGiaoVien, String maKhoaHoc, String maBaiHoc) throws SQLException {
		String sql = "select kh.GiaoVien, COUNT(kh.MaHocVien) as SLHocVien\r\n"
				+ "from BaiHoc as bh\r\n"
				+ "full outer join (\r\n"
				+ "select dk.MaHocVien, kh.*\r\n"
				+ "from DangKyKhoaHoc dk\r\n"
				+ "full outer join KhoaHoc kh on kh.MaKhoaHoc = dk.MaKhoaHoc\r\n"
				+ "where kh.GiaoVien=? AND kh.MaKhoaHoc=?) as kh on kh.MaKhoaHoc = bh.MaKhoaHoc\r\n"
				+ "where bh.MaBaiHoc=?\r\n"
				+ "group by kh.GiaoVien";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maGiaoVien);
		pstm.setString(2, maKhoaHoc);
		pstm.setString(3, maBaiHoc);
		ResultSet rs = pstm.executeQuery();
		if(rs.next())
			return rs.getInt("SLHocVien");
		return null;
	}
	
	public static List<HocVien> DanhSachHocVienDangKyGiaoVien(Connection conn, String maGiaoVien, String maKhoaHoc, String maBaiHoc) throws SQLException{
		String sql = "select hv.* from HocVien as hv\r\n"
				+ "inner join (\r\n"
				+ "select kh.*, bh.MaBaiHoc\r\n"
				+ "from BaiHoc as bh\r\n"
				+ "full outer join (\r\n"
				+ "select dk.MaHocVien, kh.*\r\n"
				+ "from DangKyKhoaHoc dk\r\n"
				+ "full outer join KhoaHoc kh on kh.MaKhoaHoc = dk.MaKhoaHoc\r\n"
				+ "where kh.GiaoVien=? AND kh.MaKhoaHoc=?) as kh on kh.MaKhoaHoc = bh.MaKhoaHoc\r\n"
				+ "where bh.MaBaiHoc=?) kh on kh.MaHocVien = hv.MaHocVien";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maGiaoVien);
		pstm.setString(2, maKhoaHoc);
		pstm.setString(3, maBaiHoc);
		ResultSet rs = pstm.executeQuery();
		
		List<HocVien> listHV = new ArrayList<HocVien>();
		while(rs.next()) {
			HocVien hv = new HocVien();
			hv.setMaHocVien(rs.getString("MaHocVien"));
			hv.setTenHocVien(rs.getNString("TenHocVien"));
			hv.setNgaySinh(rs.getDate("NgaySinh"));
			hv.setSdt(rs.getString("SDT"));
			hv.setEmail(rs.getString("Email"));
			hv.setImage(rs.getString("Image"));
			listHV.add(hv);
		}
		return listHV;
	}
	
	public static List<SoLuongHocVienDangKyKhoaHoc> DanhSachSoLuongHocVienDangKyKhoaHoc(Connection conn) throws SQLException{
		String sql = "select gv.TenGiaoVien, tk.MaKhoaHoc, HinhAnhMoTa ,tk.TenKhoaHoc, tk.SLDangKy from GiaoVien as gv\r\n"
				+ "inner join (\r\n"
				+ "select kh.*, tk.SLDangKy from KhoaHoc as kh\r\n"
				+ "inner join(\r\n"
				+ "select top 5 dk.MaKhoaHoc, COUNT(dk.MaHocVien) as SLDangKy\r\n"
				+ "from DangKyKhoaHoc as dk\r\n"
				+ "group by dk.MaKhoaHoc\r\n"
				+ "order by SLDangKy DESC) tk on tk.MaKhoaHoc=kh.MaKhoaHoc ) tk on tk.GiaoVien = gv.MaGiaoVien";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<SoLuongHocVienDangKyKhoaHoc> listSoLuong = new ArrayList<SoLuongHocVienDangKyKhoaHoc>();
		while(rs.next()) {
			SoLuongHocVienDangKyKhoaHoc soLuong = new SoLuongHocVienDangKyKhoaHoc();
			soLuong.setMaKhoaHoc(rs.getString("MaKhoaHoc"));
			soLuong.setGiaoVien(rs.getNString("TenGiaoVien"));
			soLuong.setTenKhoaHoc(rs.getNString("TenKhoaHoc"));
			soLuong.setSoLuongDangKy(rs.getInt("SLDangKy"));
			soLuong.setHinhAnhMoTa(rs.getString("HinhAnhMoTa"));
			listSoLuong.add(soLuong);
		}
		return listSoLuong;
	}
	
	 public static GiaoVien findGiaoVienByCourse(Connection conn, String courseId) throws SQLException {
	        String sql = "Select GiaoVien.* from GiaoVien join KhoaHoc on GiaoVien.MaGiaoVien = KhoaHoc.GiaoVien where MaKhoaHoc=?";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        
	        pstm.setString(1, courseId);
	        
	        //"Select a.id, a.name, a.address from Student a where a.id=12";
	        ResultSet rs = pstm.executeQuery();
	 
	        while (rs.next()) {
	        	String magv = rs.getString("MaGiaoVien");
	            String tengv = rs.getString("TenGiaoVien");  
	            String sdt = rs.getString("SDT");
	            String cccd = rs.getString("CCCD");
	            String diachi = rs.getString("DiaChi");
	            Date ngaykyket = rs.getDate("ngaykyket");
	            String email = rs.getString("Email");
	            GiaoVien gVien = new GiaoVien(magv ,email, tengv, sdt,cccd, diachi, ngaykyket);   
	            return gVien;
	        }
	        return null;
	    }
	 
	 public static List<PhanMon> findGiaoVienMajor(Connection conn, String maGiaoVien) throws SQLException {
	        String sql = "Select b.MaPhanMon, b.TenPhanMon from ChuyenMon a join PhanMon b on a.MaPhanMon=b.MaPhanMon join GiaoVien c on a.MaGiaoVien=c.MaGiaoVien where a.MaGiaoVien=?";
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, maGiaoVien);
	        //"Select a.id, a.name, a.address from Student a where a.id=12";
	        ResultSet rs = pstm.executeQuery();
	        List<PhanMon> phanMon = new ArrayList<>();
	        PhanMon temp= null;
	        
	        while (rs.next()) {
	        	temp= new PhanMon();
	        	temp.setMaPhanMon(rs.getString("MaPhanMon"));
	        	temp.setTenPhanMon(rs.getNString("TenPhanMon"));
	        	phanMon.add(temp);
	        }
	        if (temp==null)
	        	return null;
	       
	        return phanMon;
	       
	    }
	
	
	
	
}


	

