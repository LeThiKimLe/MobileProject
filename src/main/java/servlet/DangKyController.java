package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.DBUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import bean.DangNhap;
import bean.EmailUtility;
import bean.HocVien;
import dao.ConnectDataBase;

/**
 * Servlet implementation class DangKyController
 */
@WebServlet(name="signup",urlPatterns = { "/signup" })
public class DangKyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DangKyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");
		if (action == null) 
		{
			RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/Pages/DangKy.jsp");
	        dispatcher.forward(request, response);
			
		} 
		else 
		{
			if (action.equalsIgnoreCase("verify"))
			{
				doVerify(request, response);
			}
		}
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
		
		String username= (String)request.getParameter("username");
		boolean checkUser=true;
		try {
			checkUser=DBUtils.CheckUsername(conn, username);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String name= new String(request.getParameter("name").getBytes("UTF-8")) ;
		String sodt= (String)request.getParameter("sdt");
		String email= (String)request.getParameter("email");
		String ngaysinh = (String) request.getParameter("ngaysinh") ;
		if (ngaysinh=="")
			ngaysinh="2022-10-08";
		
        String pasString= (String)request.getParameter("password");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        java.sql.Date ngaySinh=null;
        java.util.Date ngaykk=null;
		try {
			ngaykk = format.parse(ngaysinh);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 try {
	        	ngaySinh = new java.sql.Date( ngaykk.getTime());
	        }
	        catch (Exception e) {
	        	
	        }
		
        HocVien signInHocVien = new HocVien(name, ngaySinh , sodt, email);
        DangNhap accountDangKy= new DangNhap(username, pasString);
        
        HttpSession session = request.getSession();
		session.setAttribute("signing_hv", signInHocVien);
		session.setAttribute("signing_account", accountDangKy);
		//setting session to expiry in 30 mins
		session.setMaxInactiveInterval(60*60*60);
		
		if (checkUser==true)
		{
			String verifyCodeString = DBUtils.getAlphaNumericString(6);
			sendEmail(email, verifyCodeString);
			session.setAttribute("otpcode", verifyCodeString);
			request.setAttribute("sendEmail", "ok");
		}
		else {
			request.setAttribute("errorStringUsername", "Tên đăng nhập đã tồn tại. Vui lòng chọn tên đăng nhập khác");
		}
			 RequestDispatcher dispatcher = request.getServletContext()
		                .getRequestDispatcher("/WEB-INF/Pages/DangKy.jsp");
		        dispatcher.forward(request, response);
	}
	
	protected void sendEmail(String email, String code) throws ServletException, IOException
	{
		ServletContext context = getServletContext();
		 String host = context.getInitParameter("host");
	     String port = context.getInitParameter("port");
	     String user = context.getInitParameter("user");
	     String pass = context.getInitParameter("pass");
		
       String subject = "Mã xác thực từ Internal Moon";
       String recipient = email;
       String content = "Chào mừng bạn đến với Internal Moon. Hãy nhập mã xác thực sau để hoàn tất đăng ký tài khoản: "+ code;

       try {
           EmailUtility.sendEmail(host, port, user, pass, recipient, subject,content);
       } catch (Exception ex) {
           ex.printStackTrace();
       }
	}
	
	protected void doVerify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
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
		
		String code= (String)request.getParameter("code");
		HttpSession session = request.getSession();
		String truecode= (String) session.getAttribute("otpcode");
		HocVien hVien=null;
		DangNhap tkDangNhap=null;
		if (code.equals(truecode))
		{
			hVien=(HocVien)session.getAttribute("signing_hv");
			try {
				hVien.setMaHocVien(hVien.autoID(conn));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			tkDangNhap= (DangNhap)session.getAttribute("signing_account");
			try {
				tkDangNhap.setIdString(tkDangNhap.autoID());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				DBUtils.YeuCauDangKy(conn, hVien, tkDangNhap);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("verifyCode", "ok");
			RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/Pages/DangNhap.jsp");
	        dispatcher.forward(request, response);
		}
		else
		{
			request.setAttribute("errorString", "Sai mã xác thực. Vui lòng nhập lại");
			request.setAttribute("verifyCode", "no");
			request.setAttribute("sendEmail", "ok");
			RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/Pages/DangKy.jsp");
	        dispatcher.forward(request, response);
		}
		
	}
}
