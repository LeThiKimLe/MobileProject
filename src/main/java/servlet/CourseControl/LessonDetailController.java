package servlet.CourseControl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import bean.BaiHoc;
import bean.BinhLuan;
import dao.ConnectDataBase;

/**
 * Servlet implementation class LessonDetailController
 */
@WebServlet (name="lessonDetail", urlPatterns = {"/lessonDetail"})
public class LessonDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LessonDetailController() {
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
			doGet_DisplayLesson(request, response);
		} else
		{
			if (action.equalsIgnoreCase("addComment")) {
				doGet_addComment(request, response);
			} else if (action.equalsIgnoreCase("repComment")) {
				doGet_repComment(request, response);
			}
			else if (action.equalsIgnoreCase("editBaiGiang"))
			{
				doGet_editBaiGiang(request, response);
			}
			else if (action.equalsIgnoreCase("editLyThuyet"))
			{
				doGet_editLyThuyet(request, response);
			}
			else if (action.equalsIgnoreCase("editBaiTap"))
			{
				doGet_editBaiTap(request, response);
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void doGet_DisplayLesson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		
		Connection conn = null;
		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException |  SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 
		String maBaiHoc = (String) request.getParameter("maBaiHoc");
		BaiHoc cur_lessonBaiHoc = new BaiHoc();
		try {
			cur_lessonBaiHoc.getAllInfor(conn, maBaiHoc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("cur_lesson", cur_lessonBaiHoc);
		if (request.getParameter("addComment")!="")
			request.setAttribute("addComment", request.getParameter("addComment"));
		if (request.getParameter("editBaiTap")!="")
			request.setAttribute("editBaiTap", request.getParameter("editBaiTap"));
		response.setContentType("text/html;charset=UTF-8");
	    RequestDispatcher dispatcher2= request.getServletContext().getRequestDispatcher("/WEB-INF/Pages/LessonDetail.jsp");
	    dispatcher2.forward(request, response);
	}
	
	protected void doGet_addComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String maBaiHocString = request.getParameter("maBaiHoc");
		System.out.print(maBaiHocString);
		String maNguoiGuiString = request.getParameter("nguoiGui");
		String tieuDeString = new String(request.getParameter("tieuDe").getBytes("UTF-8"));
		String noiDungString = new String(request.getParameter("noiDung").getBytes("UTF-8"));
		String thoiGianString = request.getParameter("thoiGian");
		
		Connection conn = null;
		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException |  SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//public BinhLuan(Connection conn, String maBaiHoc, String tieuDe, String noiDung, String nguoiGui, String thoiGian) throws SQLException
		BinhLuan newCommentBinhLuan=null;
		try {
			newCommentBinhLuan= new BinhLuan(conn, maBaiHocString, tieuDeString, noiDungString, maNguoiGuiString, thoiGianString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("lessonDetail?maBaiHoc="+maBaiHocString+"&addComment=ok");
	}
	
	protected void doGet_repComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		// TODO Auto-generated method stub
		String maBaiHocString = request.getParameter("maBaiHoc");
		
		String maNguoiGuiString = request.getParameter("nguoiGui");
		String noiDungString = new String (request.getParameter("noiDung").getBytes("UTF-8"));
		String thoiGianString = new String(request.getParameter("thoiGian").getBytes("UTF-8"));
		String maRepString = request.getParameter("maRep");
		
		Connection conn = null;
		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException |  SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		BinhLuan newCommentBinhLuan=null;
		try {
			newCommentBinhLuan= new BinhLuan(maBaiHocString, noiDungString, maNguoiGuiString, thoiGianString, maRepString,conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("lessonDetail?maBaiHoc="+maBaiHocString+"&addComment=ok");
		
	}
	
	protected void doGet_editBaiGiang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String maBaiHocString = request.getParameter("maBaiHoc");
		String linkString= request.getParameter("link");
		
		Connection conn = null;
		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException |  SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		BaiHoc bHoc= new BaiHoc(maBaiHocString);
		try {
			bHoc.SuaBaiGiang(conn, linkString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("lessonDetail?maBaiHoc="+maBaiHocString);
	}
	
	protected void doGet_editLyThuyet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String maBaiHocString = request.getParameter("maBaiHoc");
		String lyThuyetString= request.getParameter("lyThuyet");
		
		Connection conn = null;
		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException |  SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		BaiHoc bHoc= new BaiHoc(maBaiHocString);
		try {
			bHoc.SuaLyThuyet(conn, lyThuyetString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("lessonDetail?maBaiHoc="+maBaiHocString);
	}
	
	protected void doGet_editBaiTap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String maBaiHocString = request.getParameter("maBaiHoc");
		String baiTapString= request.getParameter("baiTap");
		
		Connection conn = null;
		try {
			conn = ConnectDataBase.getConnection();
		} catch (ClassNotFoundException |  SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		BaiHoc bHoc= new BaiHoc(maBaiHocString);
		try {
			bHoc.SuaBaiTap(conn, baiTapString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("lessonDetail?maBaiHoc="+maBaiHocString+"&editBaiTap=ok");
	}
	
	
	

}
