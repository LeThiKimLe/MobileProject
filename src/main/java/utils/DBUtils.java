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
import bean.API.FeedbackAPI;
import bean.API.DonHangAPI;
import bean.API.BillItem;
import bean.API.KhoiLopAPI;
import bean.API.OrderInforAPI;
import bean.API.SoDuAPI;

public class DBUtils {

	public static DangNhap YeuCauDangNhap(Connection conn, String username, String password) throws SQLException {
		String sql = "Select * from DangNhap a where a.username=? and a.password=?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, username);
		pstm.setString(2, password);

		// "Select a.id, a.name, a.address from Student a where a.id=12";
		ResultSet rs = pstm.executeQuery();
		String idString = null;
		while (rs.next()) {
			String role = rs.getString("Role");
			String id = rs.getString("Id");
			if (role.equals("HV"))
				idString = rs.getString("MaHocVien");
			else if (role.equals("GV"))
				idString = rs.getString("MaGiaoVien");
			else
				idString = rs.getString("MaQTV");

			DangNhap dn = new DangNhap(idString, id, username, password, role, false);
			return dn;
		}
		return null;
	}

	public static void YeuCauDangKy(Connection conn, HocVien hv, DangNhap dn) throws SQLException {
		String sql = "Insert into HocVien values(?, ?, ?, ?, ?, null)";

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

		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		ViThanhToan viThanhToan = new ViThanhToan(conn, hv.getMaHocVien(), 1000000, date);
		String sql3 = "Insert into ViThanhToan values(?,?,?,?)";

		PreparedStatement pstm3 = conn.prepareStatement(sql3);

		pstm3.setString(1, viThanhToan.getMaVi());
		pstm3.setString(2, viThanhToan.getHocVien());
		pstm3.setBigDecimal(3, BigDecimal.valueOf(viThanhToan.getSoDu()));
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
	
	public static boolean CheckUsername(Connection conn, String username) throws SQLException {
		String sql = "Select * from DangNhap where username=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}
	
	public static boolean CheckUsernameHocVien(Connection conn, String username) throws SQLException {
		String sql = "Select * from DangNhap where username=? and Role='HV'";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}
	
	public static boolean CheckUsernameQTV(Connection conn, String username) throws SQLException {
		String sql = "Select * from DangNhap where username=? and Role='QTV'";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}
	
	public static boolean CheckUsernameGiaoVien(Connection conn, String username) throws SQLException {
		String sql = "Select * from DangNhap where username=? and Role='GV'";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}
	
	
	
	public static String CheckUseMail(Connection conn, String username) throws SQLException {
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

	public static HocVien LayThongTin(Connection conn, String maHocVien) throws SQLException {
		String sql = "Select * from HocVien a where a.MaHocVien=?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, maHocVien);
		// "Select a.id, a.name, a.address from Student a where a.id=12";
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String ten_hocvien = rs.getString("TenHocVien");
			Date ngay_sinh = new Date(rs.getDate("NgaySinh").getTime());
			String so_dienthoai = rs.getString("SDT");
			String emailString = rs.getString("Email");
			String image = rs.getString("Image");
			HocVien hv = new HocVien(maHocVien, ten_hocvien, ngay_sinh, so_dienthoai, emailString, image);
			return hv;
		}
		return null;
	}
	
	public static List<PhanMon> LayDanhSachPhanMonTheoKhoiLop(Connection conn) throws SQLException{
		String sql = "Select pm.*, mon.TenMon\r\n"
				+ "from Mon as mon\r\n"
				+ "inner join(\r\n"
				+ "Select pm.*, kl.TenKhoi\r\n"
				+ "from PhanMon as pm\r\n"
				+ "inner join KhoiLop as kl on pm.MaKhoi = kl.MaKhoi) pm on pm.MaMon = mon.MaMon";

		PreparedStatement pstm = conn.prepareStatement(sql);
		
		ResultSet rs = pstm.executeQuery();
		
		List<PhanMon> dsPhanMon = new ArrayList<PhanMon>();
		
		while(rs.next()) {
			KhoiLop kl = new KhoiLop(rs.getString("MaKhoi"), rs.getNString("TenKhoi"));
			Mon mon = new Mon(rs.getString("MaMon"), rs.getNString("TenMon"));
			PhanMon pm = new PhanMon(rs.getString("MaPhanMon"), kl, mon, rs.getNString("TenPhanMon"));
			dsPhanMon.add(pm);
		}
		
		return dsPhanMon;
	}
	public static QuanTriVien LayThongTinQTV(Connection conn, String maQTV) throws SQLException {
		String sql = "Select * from QuanTriVien qtv where qtv.MaQTV=?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, maQTV);
		
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			QuanTriVien qtv = new QuanTriVien(rs.getString("MaQTV"), rs.getNString("HoTen"), rs.getString("SDT"), rs.getString("Email"), rs.getString("DiaChi"), rs.getString("CCCD"));
			return qtv;
		}
		return null;
	}
	
	public static GiaoVien LayThongTinGiaoVien(Connection conn, String maGiaoVien) throws SQLException {
		String sql = "Select * from GiaoVien gv where gv.MaGiaoVien=?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, maGiaoVien);
		
		ResultSet rs = pstm.executeQuery();
		

		while (rs.next()) {
			String magv = rs.getString("MaGiaoVien");
            String tengv = rs.getString("TenGiaoVien");  
            String sdt = rs.getString("SDT");
            String cccd = rs.getString("CCCD");
            String diachi = rs.getString("DiaChi");
            Date ngaykyket = rs.getDate("ngaykyket");
            String email= rs.getString("Email");
            GiaoVien gVien = new GiaoVien(magv ,tengv,sdt,cccd, diachi, ngaykyket, email, 1);  
            gVien.setListPhanMon(DBUntilQLGV.findGiaoVienMajor(conn, maGiaoVien));
            return gVien;
		}
		return null;
	}

	public static BigDecimal LaySoDu(Connection conn, String maHocVien) throws SQLException {
		String sql = "Select SoDu from ViThanhToan a where a.MaHocVien=?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, maHocVien);

		// "Select a.id, a.name, a.address from Student a where a.id=12";
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			BigDecimal sodu = rs.getBigDecimal("SoDu");
			return sodu;
		}
		return null;
	}
	
	public static SoDuAPI LaySoDuVi(Connection conn, String maHocVien) throws SQLException {
		String sql = "Select * from ViThanhToan a where a.MaHocVien=?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, maHocVien);

		// "Select a.id, a.name, a.address from Student a where a.id=12";
		ResultSet rs = pstm.executeQuery();

		while (rs.next())
		{
			int sodu = rs.getBigDecimal("SoDu").intValue();
			Date ngayCapNhat = rs.getDate("NgayCapNhat");
			SoDuAPI budget= new SoDuAPI(sodu, ngayCapNhat);
			return budget;
		}
		return null;
	}

	public static List<KhoaHoc> LayAllKhoaHoc(Connection conn) throws SQLException {
		String sql = "select a.*, b.TenGiaoVien, c.TenPhanMon from KhoaHoc a join GiaoVien b on a.GiaoVien=b.MaGiaoVien join PhanMon c on a.PhanMon=c.MaPhanMon";

		PreparedStatement pstm = conn.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();

		List<KhoaHoc> list = new ArrayList<KhoaHoc>();

		String ma_khoahocString = null;
		String giao_vienString = null;
		String phan_monString = null;
		String ten_khoahocString = null;
		String mo_taString = null;
		int so_baihoc = 0;
		int gia_tien = 0;
		Date ngay_capnhatDate = null;
		String img = null;

		while (rs.next()) {

			ma_khoahocString = rs.getString("MaKhoaHoc");
			giao_vienString = rs.getString("TenGiaoVien");
			phan_monString = rs.getString("TenPhanMon");
			ten_khoahocString = rs.getString("TenKhoaHoc");
			mo_taString = rs.getString("MoTa");
			so_baihoc = rs.getInt("SoBaiHoc");
			gia_tien = rs.getBigDecimal("GiaTien").intValue();
			ngay_capnhatDate = rs.getDate("NgayCapNhat");
			img = rs.getString("HinhAnhMoTa");

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

	public static KhoaHoc LayChiTietKhoaHoc(Connection conn, String maKhoaHoc) throws SQLException {
		String sql = "select a.HinhAnhMoTa, a.MoTa, a.GiaTien, a.NgayCapNhat, a.SoBaiHoc, a.TenKhoaHoc from KhoaHoc a where a.MaKhoaHoc=?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, maKhoaHoc);

		ResultSet rs = pstm.executeQuery();

		String ten_khoahocString = null;
		String mo_taString = null;
		int so_baihoc = 0;
		int gia_tien = 0;
		Date ngay_capnhatDate = null;
		String img = null;

		while (rs.next()) {
			ten_khoahocString = rs.getString("TenKhoaHoc");
			mo_taString = rs.getString("MoTa");
			so_baihoc = rs.getInt("SoBaiHoc");
			gia_tien = rs.getBigDecimal("GiaTien").intValue();
			ngay_capnhatDate = rs.getDate("NgayCapNhat");
			img = rs.getString("HinhAnhMoTa");

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

	public static GiaoVien LayChiTietGiaoVien(Connection conn, String maKhoaHoc) throws SQLException {
		String sql = "select b.MaGiaoVien, b.TenGiaoVien, b.SDT from KhoaHoc a join GiaoVien b on a.GiaoVien=b.MaGiaoVien where a.MaKhoaHoc=?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maKhoaHoc);
		ResultSet rs = pstm.executeQuery();

		String ma_giaovien = null;
		String ten_giaovien = null;
		String sdt = null;

		while (rs.next()) {
			ma_giaovien = rs.getString("MaGiaoVien");
			ten_giaovien = rs.getString("TenGiaoVien");
			sdt = rs.getString("SDT");

			GiaoVien mh = new GiaoVien();
			mh.setMaGiaoVien(ma_giaovien);
			mh.setTenGiaoVien(ten_giaovien);
			mh.setSdt(sdt);

			return mh;
		}
		return null;
	}

	public static List<BaiHoc> LayChiTietBaiHoc(Connection conn, String maKhoaHoc) throws SQLException {
		String sql = "select b.* from KhoaHoc a join BaiHoc b on a.maKhoaHoc=b.MaKhoaHoc where a.MaKhoaHoc=?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maKhoaHoc);
		ResultSet rs = pstm.executeQuery();

		String ma_baihoc = null;
		String ten_baihoc = null;
		String mota = null;

		List<BaiHoc> bHocs = new ArrayList<BaiHoc>();
		while (rs.next()) {
			ma_baihoc = rs.getString("MaBaiHoc");
			ten_baihoc = rs.getString("TenBaiHoc");
			mota = rs.getString("MoTaNoiDung");

			BaiHoc mh = new BaiHoc();
			mh.setMaBaiHoc(ma_baihoc);
			mh.setTenBaiHoc(ten_baihoc);
			mh.setMoTaNoiDung(mota);
			bHocs.add(mh);
		}
		return bHocs;
	}

	public static int isExisting(String id, List<KhoaHoc> list) {
		if (list == null)
			return -1;
		else {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getMaKhoaHoc().equalsIgnoreCase(id)) {
					return i;
				}
			}
			return -1;
		}
	}

	public static List<KhoiLop> LayAllKhoiLop(Connection conn) throws SQLException {
		String sql = "select * from KhoiLop";

		PreparedStatement pstm = conn.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();

		List<KhoiLop> list = new ArrayList<KhoiLop>();

		String maKhoilop = null;
		String ten_khoi = null;

		while (rs.next()) {

			maKhoilop = rs.getString("MaKhoi");
			ten_khoi = rs.getString("TenKhoi");

			KhoiLop kl = new KhoiLop();
			kl.setMaKhoi(maKhoilop);
			kl.setTenKhoi(ten_khoi);

			list.add(kl);
		}
		return list;
	}

	public static List<Mon> LayAllMonHoc(Connection conn) throws SQLException {
		String sql = "select * from Mon";

		PreparedStatement pstm = conn.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();

		List<Mon> list = new ArrayList<Mon>();

		String maMon = null;
		String tenMon = null;

		while (rs.next()) {

			maMon = rs.getString("MaMon");
			tenMon = rs.getString("TenMon");

			Mon mh = new Mon();
			mh.setMaMon(maMon);
			mh.setTenMon(tenMon);

			list.add(mh);
			System.out.print("Thanh cong");
		}
		return list;

	}

	public static List<KhoaHoc> searchByKhoiLop(Connection conn, String tenkhoi) throws SQLException {
		String sql = "select a.GiaoVien, a.GiaTien, a.HinhAnhMoTa, a.MaKhoaHoc, a.MoTa, a.NgayCapNhat, a.PhanMon, \r\n"
				+ "		a.SoBaiHoc, a.TenKhoaHoc, pm.TenPhanMon, kl.TenKhoi, gv.TenGiaoVien\r\n"
				+ "from PhanMon as pm join KhoaHoc as a on a.PhanMon = pm.MaPhanMon join KhoiLop as kl on pm.MaKhoi = kl.MaKhoi join GiaoVien as gv on gv.MaGiaoVien = a.GiaoVien\r\n"
				+ "where TenKhoi = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, tenkhoi);
		ResultSet rs = pstm.executeQuery();

		String tenKHoc = null;
		String tenGV = null;
		int giaTien = 0;
		String img = null;
		String mota = null;
		String ma_khoahocString = null;

		List<KhoaHoc> Khoahoc = new ArrayList<KhoaHoc>();
		while (rs.next()) {
			ma_khoahocString = rs.getString("MaKhoaHoc");
			tenKHoc = rs.getString("TenKhoaHoc");
			tenGV = rs.getString("TenGiaoVien");
			giaTien = rs.getBigDecimal("GiaTien").intValue();
			img = rs.getString("HinhAnhMoTa");
			mota = rs.getString("MoTa");

			KhoaHoc kh = new KhoaHoc();
			kh.setMaKhoaHoc(ma_khoahocString);
			kh.setGiaoVien(tenGV);
			kh.setTenKhoaHoc(tenKHoc);
			kh.setGiaTien(giaTien);
			kh.setHinhAnhMoTa(img);
			kh.setMoTa(mota);
			kh.setPhanMon(rs.getNString("TenPhanMon"));
			kh.setGiaTien(rs.getBigDecimal("GiaTien").intValue());
			kh.setSoBaiHoc(rs.getInt("SoBaiHoc"));

			Khoahoc.add(kh);
			System.out.print("Thanh cong1");
		}
		// System.out.print(Khoahoc);
		return Khoahoc;
	}

	public static List<KhoiLopAPI> khoaHocTheoKhoiLop(Connection conn) throws SQLException {
		List<KhoiLop> khoiLop = DBUtils.LayAllKhoiLop(conn);

		List<KhoiLopAPI> khoiLopAPI = new ArrayList<KhoiLopAPI>();

		for (int i = 0; i < khoiLop.size(); i++) {
			KhoiLopAPI klAPI = new KhoiLopAPI();
			klAPI.setKhoi(khoiLop.get(i));
			klAPI.setKhoahoc(searchByKhoiLop(conn, khoiLop.get(i).getTenKhoi()));
			khoiLopAPI.add(klAPI);
		}

		return khoiLopAPI;
	}
	
	public static List<KhoaHoc> khoaHocTheoTungKhoiLop(Connection conn, String maKhoi) throws SQLException {
		List<KhoiLopAPI> theoKhoi = khoaHocTheoKhoiLop(conn);
		List<KhoaHoc> kQ = new ArrayList<KhoaHoc>();
		for (int i = 0; i < theoKhoi.size(); i++) {
			if (theoKhoi.get(i).getKhoi().getMaKhoi().compareTo(maKhoi)==0)
				return theoKhoi.get(i).getKhoahoc();
		}
		return kQ;
	}


	public static List<KhoaHoc> searchByName(Connection conn, String name) throws SQLException {
		String sql = "select a.GiaoVien, a.GiaTien, a.HinhAnhMoTa, a.MaKhoaHoc, a.MoTa, a.NgayCapNhat,a.PhanMon,pm.TenPhanMon, a.SoBaiHoc, a.TenKhoaHoc, gv.TenGiaoVien \r\n"
				+ "from KhoaHoc as a\r\n" + "inner join GiaoVien as gv on a.GiaoVien = gv.MaGiaoVien\r\n"
				+ "join PhanMon as pm on pm.MaPhanMon=a.PhanMon where a.TenKhoaHoc like ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, "%" + name + "%");
		ResultSet rs = pstm.executeQuery();

		String tenKHoc = null;
		String tenGV = null;
		int giaTien = 0;
		String img = null;
		String mota = null;
		String pm = null;
		String ma_khoahocString = null;

		List<KhoaHoc> tenKH = new ArrayList<KhoaHoc>();
		while (rs.next()) {
			ma_khoahocString = rs.getString("MaKhoaHoc");
			tenKHoc = rs.getString("TenKhoaHoc");
			tenGV = rs.getString("TenGiaoVien");
			giaTien = rs.getBigDecimal("GiaTien").intValue();
			img = rs.getString("HinhAnhMoTa");
			mota = rs.getString("MoTa");
			pm = rs.getString("TenPhanMon");

			KhoaHoc kh = new KhoaHoc();
			kh.setMaKhoaHoc(ma_khoahocString);
			kh.setGiaoVien(tenGV);
			kh.setTenKhoaHoc(tenKHoc);
			kh.setGiaTien(giaTien);
			kh.setHinhAnhMoTa(img);
			kh.setMoTa(mota);
			kh.setPhanMon(pm);

			tenKH.add(kh);
			System.out.print("Thanh cong1");
		}
		System.out.print(tenKH);
		return tenKH;
	}

	public static ThongKeHocVienDK searchById(Connection conn, String idCourse) throws SQLException {
		String sql = "select* from dbo.PhanMon as b\r\n" + "inner join (\r\n" + "select * from GiaoVien as gv\r\n"
				+ "inner join (\r\n"
				+ "select a.MaKhoaHoc, a.TenKhoaHoc, a.MoTa, a.SoBaiHoc, a.GiaTien, a.NgayCapNhat, a.HinhAnhMoTa, a.PhanMon, a.GiaoVien, a.TrangThaiDuyet, kq1.SLHocVienDK from dbo.KhoaHoc as a\r\n"
				+ "full outer join (\r\n" + "select m.MaKhoaHoc, COUNT(m.MaHocVien) as SLHocVienDK\r\n"
				+ "from dbo.DangKyKhoaHoc as m\r\n"
				+ "group by MaKhoaHoc) kq1 on kq1.MaKhoaHoc = a.MaKhoaHoc) kq2 on gv.MaGiaoVien = kq2.GiaoVien) k on k.PhanMon = b.MaPhanMon\r\n"
				+ "where k.MaKhoaHoc like ? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, "%" + idCourse + "%");
		ResultSet rs = pstm.executeQuery();

		ThongKeHocVienDK tkKH = new ThongKeHocVienDK();
		while (rs.next()) {
			tkKH.setMaKhoaHoc(rs.getString("MaKhoaHoc"));
			tkKH.setTenKhoaHoc(rs.getNString("TenKhoaHoc"));
			tkKH.setTenPhanMon(rs.getNString("TenPhanMon"));
			tkKH.setGiaoVien(rs.getNString("TenGiaoVien"));
			tkKH.setMoTa(rs.getNString("MoTa"));
			tkKH.setSoBaiHoc(rs.getInt("SoBaiHoc"));
			tkKH.setGiaTien(rs.getBigDecimal("GiaTien").intValue());
			tkKH.setNgayCapNhat(rs.getDate("NgayCapNhat"));
			tkKH.setDaDangKy(rs.getInt("SLHocVienDK"));
			tkKH.setHinhAnhMoTa(rs.getString("HinhAnhMoTa"));
		}
		return tkKH;
	}

	public static String getAlphaNumericString(int n) {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}
	
	public static String KiemTraUser(Connection conn, String maUser) throws SQLException {
		String sql = "Select b.* from DangNhap a join HocVien b on a.MaHocVien=b.MaHocVien where b.MaHocVien=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maUser);
		ResultSet rs = pstm.executeQuery();

		if (rs.next())
			return rs.getString("MaHocVien");

		return null;
	}
	
	public static void CapNhatThongTinUser(Connection conn, HocVien hocVien) throws SQLException {
		String sql = "UPDATE HocVien\r\n"
				+ "SET TenHocVien=?, SDT=?, Email=?, NgaySinh=?, Image=?\r\n"
				+ "WHERE MaHocVien=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, hocVien.getTenHocVien());
		pstm.setString(2, hocVien.getSdt());
		pstm.setString(3, hocVien.getEmail());
		pstm.setDate(4, hocVien.getNgaySinh());
		pstm.setString(5, hocVien.getImage());
		pstm.setString(6, hocVien.getMaHocVien());
		pstm.executeUpdate();
	}
	
	public static List<PhanMon> LayDSPhanMon(Connection conn) throws SQLException {
		String sql = "Select MaPhanMon, TenPhanMon from PhanMon";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		PhanMon tkKH = new PhanMon();
		List<PhanMon> phanMon = new ArrayList<PhanMon>();
		while (rs.next()) {
			tkKH = new PhanMon();
			tkKH.setMaPhanMon(rs.getString("MaPhanMon"));
			tkKH.setTenPhanMon(rs.getNString("TenPhanMon"));
			phanMon.add(tkKH);
		}
		return phanMon;
	}
	
	public static DonHang getDonHang(Connection conn, String maDonHang) throws SQLException
	{
		String sql = "SELECT * FROM DonHang where MaDonHang=? and TinhTrangXacNhan is not null";
		PreparedStatement pstm=null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, maDonHang);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
        ResultSet rs = pstm.executeQuery();
        DonHang dh = null;
        while (rs.next()) {
        	dh=new DonHang();
			dh.setMaDonHang(rs.getString("MaDonHang"));
			dh.setTongSoTien(rs.getBigDecimal("TongSoTien").intValue());
			dh.setTinhTrangXacNhan(rs.getBoolean("TinhTrangXacNhan"));
			dh.setNgayThanhToan(rs.getDate("NgayThanhToan"));
			dh.setHocVien(rs.getString("MaHocVien"));
			dh.setNgayTao(rs.getDate("NgayTao"));
		}
        if (dh==null)
        {
        	sql = "SELECT * FROM DonHang where MaDonHang=?";
    		pstm=null;
    		try {
    			pstm = conn.prepareStatement(sql);
    			pstm.setString(1, maDonHang);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	
            rs = pstm.executeQuery();
            while (rs.next()) {
            	dh=new DonHang();
    			dh.setMaDonHang(rs.getString("MaDonHang"));
    			dh.setTongSoTien(rs.getBigDecimal("TongSoTien").intValue());
    			dh.setNgayThanhToan(rs.getDate("NgayThanhToan"));
    			dh.setHocVien(rs.getString("MaHocVien"));
    			dh.setNgayTao(rs.getDate("NgayTao"));
    		}
        }
        	
		return dh;
	}


	public static void ThanhToanHoaDon(Connection conn, String maHoaDon, String maHocVien) throws SQLException 
	{
		DonHang des = null;
		try {
			des= getDonHang(conn, maHoaDon);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int newSodu= LaySoDu(conn, maHocVien).intValue() - des.getTongSoTien();
        String sql = "Update ViThanhToan set SoDu=?, NgayCapNhat=GETDATE() where MaHocVien=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setBigDecimal(1, BigDecimal.valueOf(newSodu));
        pstm.setString(2, maHocVien);
        pstm.executeUpdate();
        ViThanhToan viThanhToan= new ViThanhToan(conn, maHocVien);
        viThanhToan.PhatSinhGiaoDich(conn, maHoaDon, des.getTongSoTien());
 
        sql = "Update DonHang set NgayThanhToan=GETDATE() where MaDonHang=?";
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, maHoaDon);
        pstm.executeUpdate();
    
    }
	
	public static int checkHoaDon(Connection conn, String maHoaDon, String maHocVien) throws SQLException
	{
		DonHang dh = getDonHang(conn,maHoaDon);
		if (dh==null)
			return 0; //Đơn không hợp lệ
		
		if (!dh.getHocVien().equals(maHocVien))
			return -1;
		
		// Đơn đã được duyệt
		if (dh.getTinhTrangXacNhan()!=null)
		{
			if (dh.getTinhTrangXacNhan()==true) 
				if (dh.getNgayThanhToan()!=null) //Đơn đã thanh toán
					return 1;
				else
					return 2;  //Đơn chưa thanh toán ->ok
			else
				return 3;  //Đơn chưa được duyệt
		}
		else
			return 4; //Đơn bị từ chối
	}
	
	public static List<String> LayDSDaDK(Connection conn, String maHoaDon) throws SQLException
	{
		String sql = "Select * from DoTrongDonHang where maDonHang=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1,maHoaDon);
		ResultSet rs = pstm.executeQuery();

		String maKH = null;
		List<String> listKH = new ArrayList<String>();
		while (rs.next()) {
			maKH=rs.getString("MaKhoaHoc");
			listKH.add(maKH);
			
		}
		return listKH;
	}
	
	public static List<FeedbackAPI> LayDanhSachFeedBack(Connection conn, String maKhoaHoc) throws SQLException{
		String sql = "select * \r\n"
				+ "from Rate as rate\r\n"
				+ "left outer join HocVien as hv on hv.MaHocVien = rate.HocVien\r\n"
				+ "where rate.KhoaHoc=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1,maKhoaHoc);
		ResultSet rs = pstm.executeQuery();
		List<FeedbackAPI> listFeedback = new ArrayList<FeedbackAPI>();
		while(rs.next()) {
			FeedbackAPI feedbackAPI = new FeedbackAPI();
			feedbackAPI.setTenNguoiDungFeedback(rs.getNString("TenHocVien"));
			feedbackAPI.setStarRate(rs.getString("Rate"));
			feedbackAPI.setComment(rs.getString("Comment"));
			listFeedback.add(feedbackAPI);
		}
		return listFeedback;
	}
	
	public static List<DonHang> LayDanhSachDonHangChuaXacNhan(Connection conn) throws SQLException{
		String sql = "select * \r\n"
				+ "from DonHang as dh\r\n"
				+ "where dh.TinhTrangXacNhan=0";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<DonHang> listDH = new ArrayList<DonHang>();
		while(rs.next()) {
			DonHang dh = new DonHang();
			dh.setMaDonHang(rs.getString("MaDonHang"));
			dh.setTongSoTien(rs.getBigDecimal("TongSoTien").intValue());
			dh.setNgayThanhToan(rs.getDate("NgayThanhToan"));
			dh.setHocVien(rs.getString("MaHocVien"));
			dh.setNgayTao(rs.getDate("NgayTao"));
			dh.setTinhTrangXacNhan(rs.getBoolean("TinhTrangXacNhan"));
			listDH.add(dh);
		}
		return listDH;
	}
	
	public static List<BillItem> getBillItem(Connection conn, String maHoaDon) throws SQLException
	{
		
		String sql = "Select kh.MaKhoaHoc, TenKhoaHoc, GiaTien from KhoaHoc kh join DoTrongDonHang it on kh.MaKhoaHoc=it.MaKhoaHoc where maDonHang=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1,maHoaDon);
		ResultSet rs = pstm.executeQuery();

		List<BillItem> listKH = new ArrayList<>();
		BillItem item= null;
		while (rs.next()) {
			item= new BillItem();
			item.setMaKhoaHoc(rs.getString("MaKhoaHoc"));
			item.setTenKhoaHoc(rs.getNString("TenKhoaHoc"));
			item.setGiaTien(rs.getBigDecimal("GiaTien").intValue());
			listKH.add(item);
		}
		return listKH;
	}
	
	public static DonHangAPI getBillInfor(Connection conn, String maHoaDon) throws SQLException
	{
		DonHang dh= getDonHang(conn, maHoaDon);
		List<BillItem> list_item= getBillItem(conn, maHoaDon);
		DonHangAPI bill = new DonHangAPI();
		bill.setDonHang(dh);
		bill.setHangDat(list_item);
		return bill;
	}
	
	public static OrderInforAPI getOrder(Connection conn, String maHoaDon) throws SQLException
	{
		DonHang dh= getDonHang(conn, maHoaDon);
		List<BillItem> list_item= getBillItem(conn, maHoaDon);
		OrderInforAPI bill = new OrderInforAPI();
		bill.setListItem(list_item);
		HocVien hv = LayThongTin(conn, dh.getHocVien());
		bill.setHocVien(hv);
		return bill;
	}
	
	public static void XacNhanDonHang(Connection conn, String maDonHang) throws SQLException{
		String sql = "update DonHang\r\n"
				+ "set TinhTrangXacNhan=1\r\n"
				+ "where MaDonHang=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1,maDonHang);
		pstm.executeUpdate();
	}
	
	public static void HoanXacNhanDonHang(Connection conn, String maDonHang) throws SQLException{
		String sql = "update DonHang\r\n"
				+ "set TinhTrangXacNhan=NULL\r\n"
				+ "where MaDonHang=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1,maDonHang);
		pstm.executeUpdate();
	}
	
	public static Boolean CheckDonHang(Connection conn, String maDonHang) throws SQLException{
		String sql = "select * from DonHang where MaDonHang=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1,maDonHang);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			return true;
		}
		return false;
	}
	
	public static List<GiaoDich> getTransaction(Connection conn, String maVi) throws SQLException
	{
		String sql = "Select * from GiaoDich where MaVi=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1,maVi);
		ResultSet rs = pstm.executeQuery();

		List<GiaoDich> listGD = new ArrayList<>();
		GiaoDich item= null;
		while (rs.next()) {
			item= new GiaoDich();
			item.setMaGiaoDich(rs.getString("MaGiaoDich"));
			item.setNgayGiaoDich(rs.getDate("NgayGiaoDich"));
			item.setNoiDungGiaoDich(rs.getNString("NoiDungGiaoDich"));
			item.setSoDuCapNhat(rs.getBigDecimal("SoDuCapNhat"));
			item.setSoTienGiaoDich(rs.getBigDecimal("SoTienGiaoDich"));
			listGD.add(item);
		}
		return listGD;
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
	
}
