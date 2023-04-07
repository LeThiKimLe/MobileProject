package servlet.AdminControl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUntilQLGV;
import utils.DBUntilQLKH;
import utils.DBUtils;

import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.env.IModule.IService;

import com.oracle.wls.shaded.org.apache.xalan.xsltc.compiler.sym;

import bean.GiaoVien;
import bean.KhoaHoc;
import bean.*;

import dao.ConnectDataBase;

/**
 * Servlet implementation class AddKhoaHocController
 */

@WebServlet (name="add_course", urlPatterns = {"/add_course"})
public class AddKhoaHocController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddKhoaHocController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Connection conn = null;
		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<GiaoVien> listgv = null;
		 List<PhanMon> list = null;
		// String makh= (String)request.getParameter("maKhoaHoc");
		// if(makh==null)
		// {
		//	 makh="KH001";
		// }
		 
	        try {
	
	            list =DBUntilQLKH.listPhanMon(conn);
	            listgv = DBUntilQLKH.listGiaoVien(conn);
	           
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	           
	        } 
	       
	       request.setAttribute("phanmon", list);
	       request.setAttribute("giaovien", listgv);
	       
		
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Pages/AddKhoaHoc.jsp");
		dispatcher.forward(request, response);
        
 
    }
 
  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String makh= (String)request.getParameter("maKhoaHoc");
        String phanmonString=new String(request.getParameter("phanMon").getBytes("UTF-8"));
        String tenkh = new String(request.getParameter("tenKhoaHoc").getBytes( "UTF-8"));
        String mota = new String(request.getParameter("moTa").getBytes("UTF-8"));
        String giatien=new String(request.getParameter("giaTien").getBytes("UTF-8"));
        String giaovienString =new String(request.getParameter("giaoVien").getBytes("UTF-8"));
        String ngaycapnhatString = (String) request.getParameter("ngayCapNhat") ;  
        java.sql.Date ngaykkDate=null;
        java.util.Date ngaykk=null;
		try {
			ngaykk = format.parse(ngaycapnhatString);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int gia=0;
        try {
        	ngaykkDate = new java.sql.Date( ngaykk.getTime() );
        	gia=Integer.parseInt(giatien);
        }
        catch (Exception e) {
        	
        }
        
       KhoaHoc kh=new KhoaHoc(makh, giaovienString, phanmonString, tenkh, mota, null, gia, ngaykkDate, null);
        String errorString = null;
        
       try {
            DBUntilQLKH.insertKhoaHoc(conn, kh);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
       request.setAttribute("pm",phanmonString);
        request.setAttribute("errorString", errorString);
       
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/Pages/AddKhoaHoc.jsp");
            dispatcher.forward(request, response);
        }
      
        else {
            response.sendRedirect(request.getContextPath() + "/QLKH");
        }
	
	}

}
