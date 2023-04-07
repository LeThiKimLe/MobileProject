package servlet.DangNhapControl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;


import bean.*;
import dao.ConnectDataBase;
import utils.*;

/**
 * Servlet implementation class DangNhapController
 */
@WebServlet(name="login",urlPatterns = { "/login" })
public class DangNhapController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DangNhapController() {
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
			response.setContentType("text/html;charset=UTF-8");
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Pages/DangNhap.jsp");
	        dispatcher.forward(request, response);
		}
		else if(action.equalsIgnoreCase("getPass"))
		{
			getPass(request, response);
		}
		else if(action.equalsIgnoreCase("verify"))
		{
			doVerify(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
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
			
	        String username= (String)request.getParameter("username");
	        String password = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");
	        DangNhap accountDangNhap=null;
	        String errorString = null;
	        try
	        {
	        	accountDangNhap=DBUtils.YeuCauDangNhap(conn,username , password);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            errorString = e.getMessage();
	        }
	        if (accountDangNhap==null)
	        	errorString="Tên đăng nhập hoặc mật khẩu sai";
	        
	        request.setAttribute("errorString", errorString);
	        request.setAttribute("username", username);
	       
	        if (errorString != null) {
	            RequestDispatcher dispatcher = request.getServletContext()
	                    .getRequestDispatcher("/WEB-INF/Pages/DangNhap.jsp");
	            dispatcher.forward(request, response);
	        }
	        else 
	        {
	        	HttpSession session = request.getSession();
				session.setAttribute("username", username);
				session.setMaxInactiveInterval(60*60*60);
				
				Cookie usernameCookie = new Cookie("username", username);
				usernameCookie.setMaxAge(31536000);
				response.addCookie(usernameCookie);
				
				Cookie userID = new Cookie("userID", accountDangNhap.getId());
				userID.setMaxAge(31536000);
				response.addCookie(userID);
				
				session.setAttribute("usercode", accountDangNhap.getId());
				
				Cookie role = new Cookie("role", accountDangNhap.getRole());
				role.setMaxAge(31536000);
				response.addCookie(role);
				session.setAttribute("role", accountDangNhap.getRole());
				if (accountDangNhap.getRole().equals("HV"))
				{
					Cookie sodu = new Cookie("soduvi", String.valueOf(new HocVien(accountDangNhap.getId()).LaySoDuVi(conn)));
					sodu.setMaxAge(31536000);
					response.addCookie(sodu);
				
					HocVien loginHocVien= new HocVien(accountDangNhap.getId());
					List<KhoaHoc> list_kh =new ArrayList<KhoaHoc>();
					try
					{
						list_kh=loginHocVien.DanhSachDangKy(conn);
					} catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					session.setAttribute("registed_course", list_kh);
					try {
						session.setAttribute("userFullName", loginHocVien.getName(conn,  accountDangNhap.getId()));
						session.setAttribute("userSDT", loginHocVien.getSDT(conn));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				response.sendRedirect(request.getContextPath() +"/home");
			}
	        
		}
	protected void getPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
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
		
		String username= (String)request.getParameter("username");
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		
		boolean checkUser=true;
		try {
			checkUser=DBUtils.CheckUsername(conn, username);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (checkUser==false)
		{
			String email=null;
			try {
				email= DBUtils.CheckUseMail(conn, username);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String verifyCodeString = DBUtils.getAlphaNumericString(6);
			sendEmail(email, verifyCodeString);
			session.setAttribute("otpcode", verifyCodeString);
			request.setAttribute("sendEmail", "ok");
		}
		else {
			request.setAttribute("errorString", "Tên đăng nhập không tồn tại. Vui lòng kiểm tra lại");
		}
			
		RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/Pages/DangNhap.jsp");
        dispatcher.forward(request, response);
		
	}
	
	protected void doVerify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
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
		String newpass= (String)request.getParameter("newpass");
		String code= (String)request.getParameter("code");
		HttpSession session = request.getSession();
		String truecode= (String) session.getAttribute("otpcode");
		if (code.equals(truecode))
		{
			try {
				DBUtils.YeuCauDoiMatKhau(conn, (String)session.getAttribute("username"), newpass);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("getPass", "ok");
		}
		else
		{
			request.setAttribute("errorString2", "Sai mã xác thực. Vui lòng nhập lại");
			request.setAttribute("getPass", "no");
			request.setAttribute("sendEmail", "ok");
		}
		RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/Pages/DangNhap.jsp");
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
       String content = "Yêu cầu cấp lại mật khẩu của bạn đã được ghi nhận. Hãy nhập mã xác thực sau để lấy lại mật khẩu: "+ code;

       try {
           EmailUtility.sendEmail(host, port, user, pass, recipient, subject,content);
       } catch (Exception ex) {
           ex.printStackTrace();
       }
	}
	
}
