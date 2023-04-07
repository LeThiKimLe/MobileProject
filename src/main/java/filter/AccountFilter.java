package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.DBUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.plugins.tiff.BaselineTIFFTagSet;

import bean.DangNhap;
import bean.HocVien;
import bean.KhoaHoc;
import dao.ConnectDataBase;

/**
 * Servlet Filter implementation class AccountFilter
 */
@WebFilter(urlPatterns = { "/*" })
public class AccountFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AccountFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest req = (HttpServletRequest) request;
		
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
		
        HttpSession session1 = req.getSession(false);
        String maUserString="";
        if(session1 == null)
        {
        	Cookie[] cookies = req.getCookies();
        	if(cookies!=null)
        	{
        		HttpSession session = req.getSession();
        		session.setMaxInactiveInterval(60*60*60);
        		for(Cookie cookie : cookies)
    	    	{
        			System.out.print(cookie.getName());
    	    		if(cookie.getName().equals("username"))
    	    		{
    	    			session.setAttribute("username", cookie.getValue());
    	    		}
    	    		
    	    		if(cookie.getName().equals("userID"))
    	    		{
    	    			maUserString=cookie.getValue();
    	    			session.setAttribute("userID", maUserString);
    	    		}
    	    		
    	    		if(cookie.getName().equals("role"))
    	    		{
    	    			session.setAttribute("role", maUserString);
    	    			HocVien loginHocVien= new HocVien();
    	    			if(cookie.getValue().equals("HV"))
    	    			{
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
    	    			}
    	    			try {
    						session.setAttribute("userFullName", loginHocVien.getName(conn,maUserString ));
    						session.setAttribute("userSDT", loginHocVien.getSDT(conn));
    					} catch (SQLException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    	    		}
    	    		
    	    	}
        	}
        }
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
