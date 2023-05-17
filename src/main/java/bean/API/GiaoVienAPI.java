package bean.API;

import bean.GiaoVien;

public class GiaoVienAPI {
	private String result;
	private String message;
	private GiaoVien giaoVien;
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
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the giaoVien
	 */
	public GiaoVien getGiaoVien() {
		return giaoVien;
	}
	/**
	 * @param giaoVien the giaoVien to set
	 */
	public void setGiaoVien(GiaoVien giaoVien) {
		this.giaoVien = giaoVien;
	}
}
