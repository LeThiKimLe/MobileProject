package bean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import dao.ConnectDataBase;
public class BinhLuan 
{
	private String maBaiHocString;
	private String maBinhLuan;
	private String tieuDeString;
	private String noiDungString;
	private String nguoiGuiString;
	private Timestamp ngayDangTimestamp;
	private BinhLuan rootBinhLuan;
	private List<BinhLuan> repList;
	
	public String getMaBinhLuan() {
		return maBinhLuan;
	}
	public void setMaBinhLuan(String maBinhLuan) {
		this.maBinhLuan = maBinhLuan;
	}
	public String getTieuDeString() {
		return tieuDeString;
	}
	public void setTieuDeString(String tieuDeString) {
		this.tieuDeString = tieuDeString;
	}
	public String getNoiDungString() {
		return noiDungString;
	}
	public void setNoiDungString(String noiDungString) {
		this.noiDungString = noiDungString;
	}
	public String getNguoiGuiString() {
		return nguoiGuiString;
	}
	public void setNguoiGuiString(String nguoiGuiString) {
		this.nguoiGuiString = nguoiGuiString;
	}
	public Timestamp getNgayDangTimestamp() {
		return ngayDangTimestamp;
	}
	public void setNgayDangTimestamp(Timestamp ngayDangTimestamp) {
		this.ngayDangTimestamp = ngayDangTimestamp;
	}
	public BinhLuan getRootBinhLuan() {
		return rootBinhLuan;
	}
	public void setRootBinhLuan(BinhLuan rootBinhLuan) {
		this.rootBinhLuan = rootBinhLuan;
	}
	public List<BinhLuan> getRepList() {
		return repList;
	}
	public void setRepList(List<BinhLuan> repList) {
		this.repList = repList;
	}
	
	public String autoID(Connection conn) throws SQLException
	{	
		String sql = "SELECT COUNT(*) as SoLuong FROM BinhLuan";
		
		PreparedStatement pstm=null;
		try {
			pstm = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ResultSet rs = pstm.executeQuery();
        String kqString="";
        while (rs.next()) 
        {
        	int soluong = rs.getInt("SoLuong");
        	if (soluong+1<10)
        		kqString= "BL000"+ String.valueOf(soluong+1);
        	else {
				kqString= "BL00"+ String.valueOf(soluong+1);
			}
        }
        return kqString;  
	}
	
	public BinhLuan()
	{
		
	}
	
	public BinhLuan(String maBinhLuan)
	{
		this.maBinhLuan=maBinhLuan;
	}

	public BinhLuan(Connection conn, String maBaiHoc, String tieuDe, String noiDung, String nguoiGui, String thoiGian) throws SQLException
	{
		this.maBinhLuan=autoID(conn);
		this.maBaiHocString=maBaiHoc;
		this.tieuDeString=tieuDe;
		this.noiDungString=noiDung;
		this.nguoiGuiString=getNguoiBinhLuan(conn, nguoiGui);
		this.ngayDangTimestamp= Timestamp.valueOf(thoiGian);
		addComment(conn);
		
	}
	
	public BinhLuan(String maBaiHoc, String noiDung, String nguoiGui, String thoiGian,String maRep, Connection conn) throws SQLException
	{
		this.maBinhLuan=autoID(conn);
		this.maBaiHocString=maBaiHoc;
		this.noiDungString=noiDung;
		this.nguoiGuiString=getNguoiBinhLuan(conn, nguoiGui);
		this.ngayDangTimestamp= Timestamp.valueOf(thoiGian);
		this.rootBinhLuan= new BinhLuan();
		this.rootBinhLuan.setMaBinhLuan(maRep);
		addRep(conn);
	}
	
	public BinhLuan(String maBinhLuan, String maBaiHoc, String tieuDe, String noiDung, String nguoiGui, Timestamp ngayGui, String maRep) throws SQLException
	{
		this.maBaiHocString=maBaiHoc;
		this.maBinhLuan=maBinhLuan;
		this.tieuDeString=tieuDe;
		this.noiDungString=noiDung;
		this.nguoiGuiString=nguoiGui;
		this.ngayDangTimestamp= ngayGui;
		this.rootBinhLuan= new BinhLuan();
		this.rootBinhLuan.setMaBinhLuan(maRep);
	}
	
	
	
	public void getChild(Connection conn) throws SQLException
	{
		String sql = "Select * from BinhLuan a where a.MaRep=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, this.maBinhLuan);
        ResultSet rs = pstm.executeQuery();
        
        List<BinhLuan> list = new ArrayList<BinhLuan>();
	    
        String maBinhLuanString="";
    	String tieuDeString="";
    	String noiDungString="";
    	String nguoiGuiString="";
    	String maBaiHocString="";
    	Timestamp ngayDangTimestamp=null;
    	String rootBinhLuan= "";
    	BinhLuan tempBinhLuan=null;
    	
        while (rs.next())
        {
        	maBinhLuanString=rs.getString("MaBinhLuan");
        	maBaiHocString=rs.getString("MaBaiHoc");
        	tieuDeString=rs.getString("TieuDe");
        	noiDungString= rs.getString("NoiDung");
        	rootBinhLuan=rs.getString("MaRep");
        	ngayDangTimestamp= (Timestamp) rs.getObject("ThoiGian");
        	nguoiGuiString=rs.getString("NguoiBinhLuan");
        	tempBinhLuan=new BinhLuan(maBinhLuanString, maBaiHocString, tieuDeString, noiDungString, nguoiGuiString, ngayDangTimestamp, rootBinhLuan );
        	list.add(tempBinhLuan);
        }
        this.repList=list;
	}
	
	public void getAllchild(Connection conn) throws SQLException
	{
		getChild(conn);
		if (this.repList.size()!=0)
		{
			for (int i = 0; i<this.repList.size(); i++)
			{
				this.repList.get(i).getAllchild(conn);
			}
		}
	}
	
	public String getMaBaiHocString() {
		return maBaiHocString;
	}
	public void setMaBaiHocString(String maBaiHocString) {
		this.maBaiHocString = maBaiHocString;
	}
	
	public String getNguoiBinhLuan(Connection conn,String userID) throws SQLException
	{
		String sql = "Select * from HocVien a where a.MaHocVien=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userID);
        ResultSet rs = pstm.executeQuery();
        if (rs.next())
        	return rs.getString("TenHocVien");
        
        String sql2 = "Select * from GiaoVien a where a.MaGiaoVien=?";
        PreparedStatement pstm2 = conn.prepareStatement(sql2);
        pstm2.setString(1, userID);
        ResultSet rs2 = pstm2.executeQuery();
        if (rs2.next())
        	return rs2.getString("TenGiaoVien");
        return null;
	}
	
	public void addComment(Connection conn) throws SQLException
	{
	 	String sql = "Insert into BinhLuan values(?,?,?,?,?,null,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, this.maBinhLuan);
        pstm.setString(2, this.maBaiHocString);
        pstm.setString(3, this.nguoiGuiString);
        pstm.setString(4, this.tieuDeString);
        pstm.setString(5, this.noiDungString);
        pstm.setTimestamp(6, this.ngayDangTimestamp);
        pstm.executeUpdate();
	}
	
	public void addRep(Connection conn) throws SQLException
	{
		String sql = "Insert into BinhLuan values(?,?,?,null,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, this.maBinhLuan);
        pstm.setString(2, this.maBaiHocString);
        pstm.setString(3, this.nguoiGuiString);
        pstm.setString(4, this.noiDungString);
        pstm.setString(5, this.rootBinhLuan.maBinhLuan);
        pstm.setTimestamp(6, this.ngayDangTimestamp);
        pstm.executeUpdate();
	}
	
	

}
