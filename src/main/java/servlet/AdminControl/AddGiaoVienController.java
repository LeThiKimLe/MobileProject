package servlet.AdminControl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.taglibs.standard.tag.el.fmt.FormatDateTag;

import bean.GiaoVien;


import utils.DBUntilQLGV;
import utils.DBUtils;
import dao.*;

/**
 * Servlet implementation class AddGiaoVienController
 */
@WebServlet (name="add_teacher", urlPatterns = {"/add_teacher"})
public class AddGiaoVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGiaoVienController() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GiaoVien gVien;
		try {
			gVien = new GiaoVien();
			String idStr = gVien.autoID();
			request.setAttribute("maGiaoVien", idStr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Pages/AddGiaoVien.jsp");
        dispatcher.forward(request, response);

	}

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
        
        String tengv = new String(request.getParameter("tenGiaoVien").getBytes("UTF-8"));
        String sdt = new String(request.getParameter("sdt").getBytes( "UTF-8"));
        String email = new String(request.getParameter("email").getBytes( "UTF-8"));
        String cccdString=new String(request.getParameter("cccd").getBytes("UTF-8"));
        String diachi=new String(request.getParameter("diaChi").getBytes( "UTF-8"));
        String ngaykyketString = (String) request.getParameter("ngayKyKet") ;
        String chuyenString=new String(request.getParameter("chuyenmon").getBytes("UTF-8"));
        
        java.sql.Date ngaykkDate=null;
        java.util.Date ngaykk=null;
		try {
			ngaykk = format.parse(ngaykyketString);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
        	ngaykkDate = new java.sql.Date( ngaykk.getTime() );
        }
        catch (Exception e) {
        	
        }
        String errorString = null;
        
        GiaoVien gVien;
		try {
			gVien = new GiaoVien(tengv, sdt, email ,cccdString, diachi, ngaykkDate, chuyenString, true);
			DBUntilQLGV.insertGiaoVien(conn, gVien);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			errorString = e1.getMessage();
		}
    
        request.setAttribute("errorString", errorString);
       
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/Pages/AddGiaoVien.jsp");
            dispatcher.forward(request, response);
        }
      
        else {
            response.sendRedirect(request.getContextPath() + "/QLGV");
        }
}
}
