package bean;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ThongKeHocVienDK {
	private String maKhoaHoc;
	private String tenKhoaHoc;
	private String tenPhanMon;
	private String giaoVien;
	private String moTa;
	private Integer soBaiHoc;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT+7")
	private Date ngayCapNhat;
	private Integer daDangKy;
	private Integer giaTien;
	private String hinhAnhMoTa;
	/**
	 * @return the maKhoaHoc
	 */
	public String getMaKhoaHoc() {
		return maKhoaHoc;
	}
	/**
	 * @param maKhoaHoc the maKhoaHoc to set
	 */
	public void setMaKhoaHoc(String maKhoaHoc) {
		this.maKhoaHoc = maKhoaHoc;
	}
	/**
	 * @return the tenKhoaHoc
	 */
	public String getTenKhoaHoc() {
		return tenKhoaHoc;
	}
	/**
	 * @param tenKhoaHoc the tenKhoaHoc to set
	 */
	public void setTenKhoaHoc(String tenKhoaHoc) {
		this.tenKhoaHoc = tenKhoaHoc;
	}
	/**
	 * @return the tenPhanMon
	 */
	public String getTenPhanMon() {
		return tenPhanMon;
	}
	/**
	 * @param tenPhanMon the tenPhanMon to set
	 */
	public void setTenPhanMon(String tenPhanMon) {
		this.tenPhanMon = tenPhanMon;
	}
	/**
	 * @return the giaoVien
	 */
	public String getGiaoVien() {
		return giaoVien;
	}
	/**
	 * @param giaoVien the giaoVien to set
	 */
	public void setGiaoVien(String giaoVien) {
		this.giaoVien = giaoVien;
	}
	/**
	 * @return the moTa
	 */
	public String getMoTa() {
		return moTa;
	}
	/**
	 * @param moTa the moTa to set
	 */
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	/**
	 * @return the soBaiHoc
	 */
	public Integer getSoBaiHoc() {
		return soBaiHoc;
	}
	/**
	 * @param soBaiHoc the soBaiHoc to set
	 */
	public void setSoBaiHoc(Integer soBaiHoc) {
		this.soBaiHoc = soBaiHoc;
	}
	/**
	 * @return the ngayCapNhat
	 */
	public Date getNgayCapNhat() {
		return ngayCapNhat;
	}
	/**
	 * @param ngayCapNhat the ngayCapNhat to set
	 */
	public void setNgayCapNhat(Date ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}
	/**
	 * @return the daDangKy
	 */
	public Integer getDaDangKy() {
		return daDangKy;
	}
	/**
	 * @param daDangKy the daDangKy to set
	 */
	public void setDaDangKy(Integer daDangKy) {
		this.daDangKy = daDangKy;
	}
	/**
	 * @return the giaTien
	 */
	public Integer getGiaTien() {
		return giaTien;
	}
	/**
	 * @param giaTien the giaTien to set
	 */
	public void setGiaTien(Integer giaTien) {
		this.giaTien = giaTien;
	}
	/**
	 * @return the hinhAnhMoTa
	 */
	public String getHinhAnhMoTa() {
		return hinhAnhMoTa;
	}
	/**
	 * @param hinhAnhMoTa the hinhAnhMoTa to set
	 */
	public void setHinhAnhMoTa(String hinhAnhMoTa) {
		this.hinhAnhMoTa = hinhAnhMoTa;
	}
	

}
