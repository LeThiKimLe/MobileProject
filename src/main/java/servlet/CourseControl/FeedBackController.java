package servlet.CourseControl;

import jakarta.servlet.RequestDispatcher;
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
import java.util.ArrayList;
import java.util.List;

import bean.KhoaHoc;
import bean.PhanHoi;
import dao.ConnectDataBase;

/**
 * Servlet implementation class FeedBackController
 */
@WebServlet (name="feedback", urlPatterns = {"/feedback"})
public class FeedBackController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedBackController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException |  SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		String ma_couse= ((KhoaHoc)session.getAttribute("current_course")).getMaKhoaHoc();
        int rate = Integer.parseInt(request.getParameter("rate"));
        String feedback = new String(request.getParameter("feedback").getBytes("UTF-8"));
        String ma_hv= (String) session.getAttribute("usercode");
        //String ma_hv= "HV0001";
        PhanHoi phanHoi=null;
        try {
			phanHoi= new PhanHoi(conn, rate, feedback, ma_hv, ma_couse);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.setAttribute("sent", "ok");
        request.setAttribute("maKhoaHoc", ma_couse);
        response.setContentType("text/html;charset=UTF-8");
        response.sendRedirect("course-detail?maKhoaHoc="+ma_couse+"&sentfeedback=ok");
        
//	    RequestDispatcher dispatcher2= request.getServletContext().getRequestDispatcher("/course-detail");
//	    dispatcher2.forward(request, response);
        
	}


}
