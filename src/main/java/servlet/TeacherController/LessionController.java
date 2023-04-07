package servlet.TeacherController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.DBUtils;
import utils.DBUtilsGiaoVien;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BaiHoc;
import bean.DangNhap;
import bean.DoTrongGioHang;
import bean.KhoaHoc;
import dao.ConnectDataBase;

/**
 * Servlet implementation class LessionController
 */
@WebServlet (name="lession", urlPatterns = {"/lession"})
public class LessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LessionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action == null) {
			doGet_DisplayLession(request, response);
		} else {
			if (action.equalsIgnoreCase("add")) {
				doGet_Add(request, response);
			} else if (action.equalsIgnoreCase("remove")) {
				doGet_Remove(request, response);
			}
		 else if (action.equalsIgnoreCase("edit")) {
			doGet_Edit(request, response);
		}
		}
	}
	
	protected void doGet_DisplayLession(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		Connection conn = null;
		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException |  SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 List<BaiHoc> list_bHocs=null;
		try {
            list_bHocs = DBUtils.LayChiTietBaiHoc(conn, ((KhoaHoc) session.getAttribute("current_course")).getMaKhoaHoc());
        } catch (SQLException e) {
            e.printStackTrace();
        }
		session.setAttribute("list_lession", list_bHocs);
		request.getRequestDispatcher("/WEB-INF/Pages/Lession.jsp").forward(request, response);
	}

	protected void doGet_Remove(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		Connection conn = null;
		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException |  SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 
		String maBaiHoc= (String)request.getParameter("id");
       
        try 
        {
			DBUtilsGiaoVien.XoaBaiHoc(conn,maBaiHoc);
		} catch (SQLException e)
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        response.sendRedirect("lession");
	}
	
	protected void doGet_Edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		Connection conn = null;
		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException |  SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String maBaiHoc= (String)request.getParameter("id");
        String tenBaiHoc = new String(request.getParameter("title").getBytes("UTF-8"));
        String motaBaiHoc = new String(request.getParameter("content").getBytes("UTF-8"));
        String maKhoaHoc = ((KhoaHoc) session.getAttribute("current_course")).getMaKhoaHoc();
        
        BaiHoc bh= new BaiHoc(maBaiHoc, maKhoaHoc, tenBaiHoc, motaBaiHoc);
        try {
			DBUtilsGiaoVien.SuaBaiHoc(conn,bh);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        response.sendRedirect("lession");
	}

	protected void doGet_Add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		Connection conn = null;
		try
		{
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException |  SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        String tenBaiHoc = new String(request.getParameter("title").getBytes("UTF-8"));
        String motaBaiHoc = new String(request.getParameter("content").getBytes("UTF-8"));
        String maKhoaHoc = ((KhoaHoc) session.getAttribute("current_course")).getMaKhoaHoc();
        
        try {
			BaiHoc bh= new BaiHoc(conn, maKhoaHoc, tenBaiHoc, motaBaiHoc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        response.sendRedirect("lession");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
