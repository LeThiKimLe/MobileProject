package bean.API;

import java.sql.Date;
import java.util.List;

import bean.DonHang;
import bean.KhoaHoc;
import bean.API.BillItem;

public class DonHangAPI {
	
	private String result;
	private String message;
	private DonHang donHang;
	private List<BillItem> hangDat;
	
	public DonHang getDonHang() {
		return donHang;
	}
	public void setDonHang(DonHang donHang) {
		this.donHang = donHang;
	}
	
	public List<BillItem> getHangDat() {
		return hangDat;
	}
	public void setHangDat(List<BillItem> hangDat) {
		this.hangDat = hangDat;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
