package bean.API;

import bean.ViThanhToan;

import java.util.List;

import bean.GiaoDich;

public class ViThanhToanAPI {
	private String result;
	private String message;
	private ViThanhToan wallet;
	private List<GiaoDich> listGD;
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
	public ViThanhToan getWallet() {
		return wallet;
	}
	public void setWallet(ViThanhToan my_wallet) {
		this.wallet = my_wallet;
	}
	public List<GiaoDich> getListGD() {
		return listGD;
	}
	public void setListGD(List<GiaoDich> listGD) {
		this.listGD = listGD;
	}
	
}