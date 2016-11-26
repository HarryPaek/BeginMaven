package spms.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/dao/delete")
public class DaoMemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		
	    try {
	    	ServletContext sc = this.getServletContext();
	    	conn = (Connection)sc.getAttribute("conn");

	    	MemberDao dao = new MemberDao();
	    	dao.setConnecion(conn);
	    	
	    	dao.delete(Integer.parseInt(request.getParameter("no")));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/member/MemberDeleteSuccess.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
			// request에 Exception 데이터 보관
			request.setAttribute("error", e);

			//Error.jsp로 위임
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request, response);
		}
	}
}
