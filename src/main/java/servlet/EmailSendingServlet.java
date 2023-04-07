package servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import bean.EmailUtility;

/**
 * Servlet implementation class EmailSendingServlet
 */
@WebServlet("/EmailSendingServlet")
public class EmailSendingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailSendingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
        // reads SMTP server setting from web.xml file
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// reads form fields
		 ServletContext context = getServletContext();
		 String host = context.getInitParameter("host");
	     String port = context.getInitParameter("port");
	     String user = context.getInitParameter("user");
	     String pass = context.getInitParameter("pass");
		
        String subject = request.getParameter("subject");
        String recipient = request.getParameter("email");
        String content = request.getParameter("message");
 
        try {
            EmailUtility.sendEmail(host, port, user, pass, recipient, subject,content);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}

}
