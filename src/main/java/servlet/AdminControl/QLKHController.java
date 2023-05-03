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
import java.util.List;

import bean.GiaoVien;
import bean.KhoaHoc;
import dao.ConnectDataBase;

/**
 * Servlet implementation class QLKHController
 */
@WebServlet (name="QLKH", urlPatterns = {"/QLKH"})
public class QLKHController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QLKHController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Connection conn = null;
				try {
					conn = ConnectDataBase.getConnection();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 List<KhoaHoc> list = null;
			        try {
			            list = DBUntilQLKH.listKhoaHoc(conn);
			            
			            
			        } catch (SQLException e) {
			            e.printStackTrace();
			           
			        }
			       request.setAttribute("QLKH", list);
				
				response.setContentType("text/html;charset=UTF-8");
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Pages/QLKH.jsp");
				dispatcher.forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
