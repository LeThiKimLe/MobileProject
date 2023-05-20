package bean.API;

import java.util.List;

import bean.HocVien;



public class OrderInforAPI {
	private HocVien hocVien;
	private List<BillItem> listItem;
	
	public HocVien getHocVien() {
		return hocVien;
	}
	
	public void setHocVien(HocVien hocVien) {
		this.hocVien = hocVien;
	}
	
	public List<BillItem> getListItem() {
		return listItem;
	}
	
	public void setListItem(List<BillItem> listItem) {
		this.listItem = listItem;
	}
	
}