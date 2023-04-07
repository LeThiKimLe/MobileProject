package servlet.AdminControl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import utils.DBUntilQLGV;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import bean.GiaoVien;
import dao.*;

/**
 * Servlet implementation class DeleteSinhVienServlet
 */
@WebServlet (name="/del_GiaoVien", urlPatterns = {"/del_GiaoVien"})
public class DelGiaoVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelGiaoVienController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Connection conn = null;
		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 
        String idStr = (String) request.getParameter("maGiaoVien");
       
       GiaoVien gv = null;
 
        String errorString = null;
 
        try {
            DBUntilQLGV.deleteGiaoVien(conn, idStr);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        response.sendRedirect(request.getContextPath() + "/QLGV");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
