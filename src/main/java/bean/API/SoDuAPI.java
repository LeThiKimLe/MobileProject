package bean.API;

import java.sql.Date;
import java.util.List;

public class SoDuAPI {
	private String result;
	private String message;
	private int soDu;
	private Date ngayCapNhat;
	public int getSoDu() {
		return soDu;
	}
	public void setSoDu(int soDu) {
		this.soDu = soDu;
	}
	public Date getNgayCapNhat() {
		return ngayCapNhat;
	}
	public void setNgayCapNhat(Date ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}
	
	public SoDuAPI(int soDuVi, Date ngayUpdate)
	{
		this.soDu= soDuVi;
		this.ngayCapNhat = ngayUpdate;
	}
	
	public SoDuAPI () {}
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
