package bean.API;

import java.io.Serializable;

import bean.HocVien;

public class HocVienAPI implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4673744252527882000L;
	private String result;
	private String message;
	private HocVien hocVien;
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
	 * @return the hocVien
	 */
	public HocVien getHocVien() {
		return hocVien;
	}
	/**
	 * @param hocVien the hocVien to set
	 */
	public void setHocVien(HocVien hocVien) {
		this.hocVien = hocVien;
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
	@Override
	public String toString() {
		return "HocVienAPI [result=" + result + ", message=" + message + ", hocVien=" + hocVien + "]";
	}
	
}
