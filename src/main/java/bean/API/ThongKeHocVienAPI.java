package bean.API;

import java.util.List;

import bean.HocVien;

public class ThongKeHocVienAPI {
	private String result;
	private Integer soNguoiHoc;
	private List<HocVien> danhSach;
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return the soNguoiHoc
	 */
	public Integer getSoNguoiHoc() {
		return soNguoiHoc;
	}
	/**
	 * @param soNguoiHoc the soNguoiHoc to set
	 */
	public void setSoNguoiHoc(Integer soNguoiHoc) {
		this.soNguoiHoc = soNguoiHoc;
	}
	/**
	 * @return the danhSach
	 */
	public List<HocVien> getDanhSach() {
		return danhSach;
	}
	/**
	 * @param danhSach the danhSach to set
	 */
	public void setDanhSach(List<HocVien> danhSach) {
		this.danhSach = danhSach;
	}


}
