package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
	    try {
	    	ServletContext sc = this.getServletContext();
	    	conn = (Connection)sc.getAttribute("conn");

	    	stmt = conn.prepareStatement("DELETE FROM MEMBERS WHERE MNO = ?;");
			
			stmt.setInt(1, Integer.parseInt(request.getParameter("no")));
			stmt.executeUpdate();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/member/MemberDeleteSuccess.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// request에 Exception 데이터 보관
			request.setAttribute("error", e);

			//Error.jsp로 위임
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request, response);
		}
	    finally {
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
		}
	}
}
