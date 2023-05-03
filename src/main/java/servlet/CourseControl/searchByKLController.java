package servlet.CourseControl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bean.KhoaHoc;
import bean.KhoiLop;
import dao.ConnectDataBase;

/**
 * Servlet implementation class SearchByKhoiController
 */
@WebServlet(name = "searchbyKL", urlPatterns = "/searchbyKL")
public class searchByKLController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchByKLController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		Connection conn = null;
		try 
		{
			conn = ConnectDataBase.getConnection();
		} 
		catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String khoi = request.getParameter("tenKhoi");
		System.out.print(khoi); 
		List<KhoaHoc> listKH = null;
        String errorString;
 
        try {
            listKH = DBUtils.searchByKhoiLop(conn, khoi);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        request.setAttribute("khoahocList", listKH);
        
        List<KhoiLop> listlop = null;
		
		 try
		 {
			listlop = DBUtils.LayAllKhoiLop(conn);
			System.out.print("Lay khoi lớp thanh cong");
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
		 request.setAttribute("khoilopList", listlop);
		 
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = request.getServletContext()
                 .getRequestDispatcher("/WEB-INF/Pages/KhoaHoc.jsp");
         dispatcher.forward(request, response);
              
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}