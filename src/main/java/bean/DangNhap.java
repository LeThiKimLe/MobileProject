package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.plugins.tiff.BaselineTIFFTagSet;

import dao.ConnectDataBase;

/**
 * DangNhap generated by hbm2java
 */
public class DangNhap implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idString;
	private String identity;
	//mã học viên hoặc giáo viên hoặc quản trị viên
	private String username;
	private String password;
	private String role;

	public DangNhap(String username, String password) {
		this.username=username;
		this.password=password;
	}

	
	public DangNhap() throws SQLException {
		String idString=autoID();
		this.identity = idString;
	}

	public String autoID() throws SQLException
	{
		Connection conn = null;
		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql = "SELECT COUNT(*) as SoLuong FROM DangNhap";
		
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
        		kqString= "TK00"+ String.valueOf(soluong+1);
        	else {
				kqString= "TK0"+ String.valueOf(soluong+1);
			}
        }
        return kqString;  
	}
	
	public DangNhap(String id, String username,String password, String role, boolean autoid) throws SQLException
	{
		if(autoid==true)
			this.idString=autoID();
		this.identity = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public DangNhap(String id, String username,String password, String role) throws SQLException
	{
		id=autoID();
		this.identity = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public String getId() {
		return this.identity;
	}

	public void setId(String id) {
		this.identity = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getIdString() {
		return idString;
	}

	public void setIdString(String idString) {
		this.idString = idString;
	}

}
