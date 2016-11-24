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
 * Servlet implementation class MemberAddServlet
 */
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//JSP로 출력을 위임
		RequestDispatcher dispatcher = request.getRequestDispatcher("/member/MemberForm.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
	    try {
	    	ServletContext sc = this.getServletContext();
	    	conn = (Connection)sc.getAttribute("conn");

	    	stmt = conn.prepareStatement("INSERT INTO MEMBERS(MNAME, EMAIL, PWD, CRE_DATE, MOD_DATE) VALUES (?, ?, ?, NOW(), NOW());");
			stmt.setString(1, request.getParameter("name"));
			stmt.setString(2, request.getParameter("email"));
			stmt.setString(3, request.getParameter("password"));
			stmt.executeUpdate();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/member/MemberAddSuccess.jsp");
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
