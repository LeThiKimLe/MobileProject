package bean.API;

import java.util.List;

import bean.KhoaHoc;
import bean.KhoiLop;

public class KhoiLopAPI {
	private KhoiLop khoi;
	private List<KhoaHoc> khoahoc;
	/**
	 * @return the khoi
	 */
	public KhoiLop getKhoi() {
		return khoi;
	}
	/**
	 * @param khoi the khoi to set
	 */
	public void setKhoi(KhoiLop khoi) {
		this.khoi = khoi;
	}
	/**
	 * @return the khoahoc
	 */
	public List<KhoaHoc> getKhoahoc() {
		return khoahoc;
	}
	/**
	 * @param khoahoc the khoahoc to set
	 */
	public void setKhoahoc(List<KhoaHoc> khoahoc) {
		this.khoahoc = khoahoc;
	}
	@Override
	public String toString() {
		return "KhoiLopAPI [khoi=" + khoi + ", khoahoc=" + khoahoc + "]";
	}
	
}
