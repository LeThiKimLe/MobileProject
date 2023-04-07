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

import bean.*;
import dao.*;

/**
 * Servlet implementation class ChiTietKhoaHocController
 */
@WebServlet (name="course-detail", urlPatterns = {"/course-detail"})
public class ChiTietKhoaHocController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChiTietKhoaHocController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException |  SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 
        String idStr = (String) request.getParameter("maKhoaHoc");
       
        KhoaHoc mh = null;
        GiaoVien gv = null;
        List<BaiHoc> list_bHocs=null;
        String errorString = null;
 
        try {
            mh = DBUtils.LayChiTietKhoaHoc(conn, idStr);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        
        try {
            gv = DBUtils.LayChiTietGiaoVien(conn, idStr);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        
        try {
            list_bHocs = DBUtils.LayChiTietBaiHoc(conn, idStr);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        
        List<PhanHoi> listPhanHoi = null;
        listPhanHoi=dogetPhanHoi(conn, idStr);
        
        HttpSession session = request.getSession();
        session.setAttribute("current_course", mh);
        request.setAttribute("khoahoc", mh);
        request.setAttribute("giaovien", gv);
        request.setAttribute("list_baihoc", list_bHocs);
        session.setAttribute("list_lession", list_bHocs);
        String stateString= (String)request.getAttribute("state");
        request.setAttribute("state", stateString);
        request.setAttribute("sent", (String)request.getParameter("sentfeedback"));
        request.setAttribute("listPhanHoi", listPhanHoi);
        float avgStar= getStarAVG(listPhanHoi);
        request.setAttribute("avgStar", avgStar);

	    response.setContentType("text/html;charset=UTF-8");
	    RequestDispatcher dispatcher2= request.getServletContext().getRequestDispatcher("/WEB-INF/Pages/ChiTietKhoaHoc.jsp");
	    dispatcher2.forward(request, response);
	   }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	 
	protected List<PhanHoi> dogetPhanHoi(Connection conn, String maKhoaString)
	{		
		List<PhanHoi> listPhanHoi = new ArrayList<PhanHoi>();
		try {
			listPhanHoi=DBUtils.TaiBinhLuan(conn, maKhoaString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listPhanHoi;
	}
	
	private float getStarAVG(List<PhanHoi> listPH)
	{
		int total= listPH.size();
		int sum=0;
		for(int i=0; i<total; i++)
		{
			sum=sum+listPH.get(i).getRate();
		}
		return (float)sum/total;
	}
	
	

}
