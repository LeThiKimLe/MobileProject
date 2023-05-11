package bean.API;

import java.util.List;

public class ThongKeSoLuongHocVienDangKyAPI {
	private int tongSoHocVien;
	private List<SoLuongHocVienDangKyKhoaHoc> dangKyNhieu;
	/**
	 * @return the tongSoHocVien
	 */
	public int getTongSoHocVien() {
		return tongSoHocVien;
	}
	/**
	 * @param tongSoHocVien the tongSoHocVien to set
	 */
	public void setTongSoHocVien(int tongSoHocVien) {
		this.tongSoHocVien = tongSoHocVien;
	}
	/**
	 * @return the dangKyNhieu
	 */
	public List<SoLuongHocVienDangKyKhoaHoc> getDangKyNhieu() {
		return dangKyNhieu;
	}
	/**
	 * @param dangKyNhieu the dangKyNhieu to set
	 */
	public void setDangKyNhieu(List<SoLuongHocVienDangKyKhoaHoc> dangKyNhieu) {
		this.dangKyNhieu = dangKyNhieu;
	}
	
	

}
