package servlet.DangNhapControl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class DangXuatController
 */
@WebServlet (name="signout", urlPatterns = {"/signout"})
public class DangXuatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DangXuatController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
    	//invalidate the session if exists
		
    	Cookie[] cookies = request.getCookies();
    	if(cookies != null)
    	{
    		
	    	for(Cookie cookie : cookies)
	    	{
	    		cookie.setMaxAge(0);
	        	response.addCookie(cookie);
	    	}
    	}
    	
    	HttpSession session = request.getSession(false);
    	if(session != null){
    		session.invalidate();
    	}
    	
    	response.sendRedirect(request.getContextPath() +"/home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
