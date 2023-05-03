package servlet.AdminControl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUntilQLGV;

import utils.DBUntilQLKH;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import bean.GiaoVien;
import bean.KhoaHoc;
import dao.ConnectDataBase;

/**
 * Servlet implementation class EditKhoaHocController
 */
@WebServlet (name="edit_course", urlPatterns = {"/edit_course"})
public class EditKhoaHocController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditKhoaHocController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Connection conn = null;
		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 
       
       
		

        String idStr = (String) request.getParameter("maKhoaHoc");
        List<GiaoVien> listgv = null;
        KhoaHoc kh=null;
 
        String errorString = null;
 
        try {
            kh=DBUntilQLKH.findKhoaHoc(conn, idStr);
            
            listgv = DBUntilQLKH.listGVtheoPM(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        
        if (errorString != null && kh == null) {
            response.sendRedirect(request.getServletPath() + "/QLKH");
            return;
        }
 
       
        request.setAttribute("errorString", errorString);
        request.setAttribute("khoahoc", kh);
        request.setAttribute("giaovien", listgv);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/Pages/EditKhoaHoc.jsp");
        dispatcher.forward(request, response);
 
    }
 
   
    @Override

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
        String errorString = null;
        KhoaHoc kh=null;
		try {
			kh = new KhoaHoc(makh, giaovienString, phanmonString, tenkh, mota, null, gia, ngaykkDate, null);
			DBUntilQLKH.updateKhoaHoc(conn, kh);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
            errorString = e1.getMessage();
		}
    
        request.setAttribute("errorString", errorString);
        request.setAttribute("khoahoc", kh);
       
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/Pages/EditKhoaHoc.jsp");
            dispatcher.forward(request, response);
        }
      
        else {
            response.sendRedirect(request.getContextPath() + "/QLKH");
        }
	

}
}
