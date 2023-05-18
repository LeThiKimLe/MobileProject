package utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bean.*;
import bean.API.BaiGiangAPI;
import bean.API.KhoaHocHocVienAPI;
import bean.API.VideoBaiHocAPI;

public class DBUntilQLKH {
	public static String pm = null;

	public static List<KhoaHoc> listKhoaHoc(Connection conn) throws SQLException {
		String sql = "select a.MaKhoaHoc,a.TenKhoaHoc,a.MoTa,a.GiaTien,a.SoBaiHoc,a.NgayCapNhat,b.TenPhanMon as PhanMon,c.TenGiaoVien as GiaoVien \r\n"
				+ "from KhoaHoc as a inner \r\n" + "join PhanMon as b on a.PhanMon=b.MaPhanMon\r\n"
				+ "join GiaoVien as c on a.GiaoVien=c.MaGiaoVien";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<KhoaHoc> list = new ArrayList<KhoaHoc>();

		while (rs.next()) {
			String makh = rs.getString("MaKhoaHoc");
			String tenkh = rs.getString("TenKhoaHoc");
			String mota = rs.getString("MoTa");
			Integer giatien = rs.getInt("GiaTien");
			Date ngaycn = rs.getDate("NgayCapNhat");
			String phanmonString = rs.getString("PhanMon");
			String giaovienString = rs.getString("GiaoVien");

			KhoaHoc kh = new KhoaHoc();
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

		List<PhanMon> list = new ArrayList<PhanMon>();

		while (rs.next()) {
			String maph = rs.getString("MaPhanMon");
			String tenph = rs.getString("TenPhanMon");

			PhanMon pMon = new PhanMon();
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

		List<GiaoVien> list = new ArrayList<GiaoVien>();

		while (rs.next()) {
			String magv = rs.getString("MaGiaoVien");
			String tengv = rs.getString("TenGiaoVien");
			String phanmonString = rs.getString("MaPhanMon");
			GiaoVien gv = new GiaoVien();
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
		System.out.print("xong+" + pm + "xog");
		pstm.setString(1, pm);
		ResultSet rs = pstm.executeQuery();

		List<GiaoVien> list = new ArrayList<GiaoVien>();

		while (rs.next()) {
			String magv = rs.getString("MaGiaoVien");
			String tengv = rs.getString("TenGiaoVien");
			String phanmonString = rs.getString("MaPhanMon");
			GiaoVien gv = new GiaoVien();
			gv.setMaGiaoVien(magv);
			gv.setTenGiaoVien(tengv);
			gv.setChuyenmon(phanmonString);
			list.add(gv);

		}

		return list;

	}

	public static KhoaHoc findKhoaHoc(Connection conn, String inmakh) throws SQLException {
		String sql = "select a.MaKhoaHoc,a.TenKhoaHoc,a.MoTa,a.SoBaiHoc,a.NgayCapNhat,a.GiaTien,a.PhanMon,a.GiaoVien,b.TenPhanMon ,c.TenGiaoVien\r\n"
				+ "from KhoaHoc as a inner \r\n" + "join PhanMon as b on a.PhanMon=b.MaPhanMon\r\n"
				+ "join GiaoVien as c on a.GiaoVien=c.MaGiaoVien\r\n" + "where a.MaKhoaHoc=?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, inmakh);

		// "Select a.id, a.name, a.address from Student a where a.id=12";
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String makh = rs.getString("MaKhoaHoc");
			String tenkh = rs.getString("TenKhoaHoc");
			String mota = rs.getString("MoTa");
			Integer giatien = rs.getInt("GiaTien");
			String phanmon = rs.getString("PhanMon");
			String giaovienString = rs.getString("GiaoVien");
			Date ngaycapnhat = rs.getDate("NgayCapNhat");

			KhoaHoc kh = new KhoaHoc(makh, giaovienString, phanmon, tenkh, mota, null, giatien, ngaycapnhat, null);
			pm = phanmon;
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
		pstm.setInt(3, kh.getGiaTien());
		pstm.setDate(4, kh.getNgayCapNhat());

		pstm.setString(5, kh.getGiaoVien());

		pstm.executeUpdate();

	}

	public static void insertKhoaHoc(Connection conn, KhoaHoc kh) throws SQLException {
		String sql = "Insert KhoaHoc(MaKhoaHoc,TenKhoaHoc,MoTa,GiaTien,NgayCapNhat,PhanMon,GiaoVien) values (?,?,?,?,?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, kh.getMaKhoaHoc());
		pstm.setString(2, kh.getTenKhoaHoc());
		pstm.setString(3, kh.getMoTa());
		pstm.setInt(4, kh.getGiaTien());
		pstm.setDate(5, kh.getNgayCapNhat());
		pstm.setString(6, kh.getPhanMon());
		pstm.setString(7, kh.getGiaoVien());
		pstm.executeUpdate();

	}

	public static boolean CheckHocVien(Connection conn, String maHocVien) throws SQLException {
		String sql = "select * from dbo.HocVien where MaHocVien = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maHocVien);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}

	/**
	 * Kiểm tra khóa học có tồn tại và được duyệt hay chưa
	 * 
	 * @param conn
	 * @param idCourse
	 * @return
	 * @throws SQLException
	 */
	public static boolean CheckCourse(Connection conn, String idCourse) throws SQLException {
		String sql = "select * from dbo.KhoaHoc Where MaKhoaHoc = ? AND TrangThaiDuyet='True'";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, idCourse);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}

	/**
	 * Kiểm tra khóa học đã được đăng ký hay chưa
	 * 
	 * @param conn
	 * @param maHocVien
	 * @param idCourse
	 * @return
	 * @throws SQLException
	 */
	public static boolean CheckDaDangKy(Connection conn, String maHocVien, String idCourse) throws SQLException {
		String sql = "select * from dbo.DangKyKhoaHoc where MaHocVien=? and MaKhoaHoc=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maHocVien);
		pstm.setString(2, idCourse);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}

	/**
	 * Thêm khóa học đã đăng ký vào
	 * 
	 * @param conn
	 * @param maHocVien
	 * @param idCourse
	 * @throws SQLException
	 */
	public static void DangKy(Connection conn, String maHocVien, String idCourse) throws SQLException {
		String sql = "insert into dbo.DangKyKhoaHoc values (?, ?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maHocVien);
		pstm.setString(2, idCourse);
		pstm.executeUpdate();
	}
	/**
	 * Danh sách khóa học mà học viên đã đăng ký
	 * @param conn
	 * @param maHocVien
	 * @return
	 * @throws SQLException
	 */
	public static List<KhoaHocHocVienAPI> DanhSachKhoaHocHocVien(Connection conn, String maHocVien)
			throws SQLException {
		String sql = "select *\r\n" + "from dbo.DangKyKhoaHoc as dk inner join (select kh.*, gv.TenGiaoVien \r\n"
				+ "from dbo.GiaoVien as gv inner join (\r\n" + "select kh.*, pm.TenPhanMon\r\n"
				+ "from dbo.KhoaHoc as kh inner join dbo.PhanMon as pm on kh.PhanMon = pm.MaPhanMon) kh on kh.GiaoVien = gv.MaGiaoVien) kh on dk.MaKhoaHoc = kh.MaKhoaHoc\r\n"
				+ "where dk.MaHocVien=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maHocVien);
		// "Select a.id, a.name, a.address from Student a where a.id=12";
		ResultSet rs = pstm.executeQuery();
		List<KhoaHocHocVienAPI> khhv = new ArrayList<KhoaHocHocVienAPI>();
		while(rs.next()) {
			KhoaHocHocVienAPI khhv1 = new KhoaHocHocVienAPI();
			khhv1.setMaKhoaHoc(rs.getString("MaKhoaHoc"));
			khhv1.setTenKhoaHoc(rs.getNString("TenKhoaHoc"));
			khhv1.setPhanMon(rs.getNString("TenPhanMon"));
			khhv1.setGiaoVien(rs.getNString("TenGiaoVien"));
			khhv1.setHinhAnhMoTa(rs.getString("HinhAnhMoTa"));
			khhv1.setGiaTien(rs.getInt("GiaTien"));
			khhv.add(khhv1);
		}
		return khhv;
	}
	
	public static List<BaiHoc> DanhSachBaiHocVien(Connection conn, String maKhoaHoc, String maHocVien)
			throws SQLException {
		String sql = "select bh.*\r\n"
				+ "from DangKyKhoaHoc as dk\r\n"
				+ "inner join BaiHoc as bh on bh.MaKhoaHoc = dk.MaKhoaHoc\r\n"
				+ "where MaHocVien=? and dk.MaKhoaHoc=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maHocVien);
		pstm.setString(2, maKhoaHoc);
		ResultSet rs = pstm.executeQuery();
		List<BaiHoc> bh = new ArrayList<BaiHoc>();
		while(rs.next()) {
			BaiHoc bh1 = new BaiHoc();
			bh1.setMaBaiHoc(rs.getString("MaBaiHoc"));
			bh1.setTenBaiHoc(rs.getNString("TenBaiHoc"));
			bh1.setMoTaNoiDung(rs.getNString("MoTaNoiDung"));
			bh1.setKhoaHoc(rs.getString("MaKhoaHoc"));
			bh.add(bh1);
		}
		return bh;
	}
	public static List<BaiHoc> DanhSachBai(Connection conn, String maKhoaHoc)
			throws SQLException {
		String sql = "select bh.TenBaiHoc,bh.MaBaiHoc "
				+ "from BaiHoc as bh "
				+ "where bh.MaKhoaHoc=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maKhoaHoc);
	
		ResultSet rs = pstm.executeQuery();
		List<BaiHoc> bh = new ArrayList<BaiHoc>();
		while(rs.next()) {
			BaiHoc bh1 = new BaiHoc();
			bh1.setMaBaiHoc(rs.getString("MaBaiHoc"));
			bh1.setTenBaiHoc(rs.getNString("TenBaiHoc"));
			
			bh.add(bh1);
		}
		return bh;
	}
	
	public static VideoBaiHocAPI VideoBaiGiangHocVien(Connection conn, String maBaiHoc)
			throws SQLException {
		String sql = "select * \r\n"
				+ "from dbo.BaiHoc\r\n"
				+ "where MaBaiHoc=? ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maBaiHoc);
		ResultSet rs = pstm.executeQuery();
		VideoBaiHocAPI videoBaiHoc = new VideoBaiHocAPI();
		 
		while(rs.next()) {
//			videoBaiHoc.setVideo(rs.getString("VideoBaiHoc"));
			videoBaiHoc.setTenBH(rs.getNString("TenBaiHoc"));
			videoBaiHoc.setDescription(rs.getNString("MoTaNoiDung"));		
		}
		return videoBaiHoc;
	}
	
	public static BaiGiangAPI BaiGiangHocVien(Connection conn, String maBaiHoc)
			throws SQLException {
		String sql = "select * \r\n"
				+ "from dbo.TaiNguyen\r\n"
				+ "where MaBaiHoc=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maBaiHoc);
		//pstm.setString(2, maKhoaHoc);
		ResultSet rs = pstm.executeQuery();
		BaiGiangAPI baiGiang = new BaiGiangAPI();
		while(rs.next()) {
			baiGiang.setDocument(rs.getNString("BaiTap"));
			baiGiang.setLyThuyet(rs.getNString("LyThuyet"));
		}
		return baiGiang;
	}
	
	public static Integer SoLuongHocVien(Connection conn) throws SQLException {
		String sql = "select COUNT(*) as SoLuong\r\n"
				+ "from dbo.HocVien";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		if(rs.next())
			return rs.getInt("SoLuong");
		return null;
	}
}
