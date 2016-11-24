package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//JSP로 출력을 위임
		RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/LoginForm.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
	    try {
	    	ServletContext sc = this.getServletContext();
	    	conn = (Connection)sc.getAttribute("conn");
            stmt = conn.prepareStatement("SELECT MNO, MNAME, EMAIL FROM MEMBERS WHERE EMAIL = ? AND PWD = ?;");
			
			stmt.setString(1, request.getParameter("email"));
			stmt.setString(2, request.getParameter("password"));
			rs = stmt.executeQuery();
			
            if(rs.next()) {
				Member member = new Member()
						       .setNo(rs.getInt("MNO"))
						       .setName(rs.getString("MNAME"))
						       .setEmail(rs.getString("EMAIL"));
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				
				response.sendRedirect(request.getContextPath() + "/member/list");
			}
            else {
    			//LoginFail.jsp로 출력 위임
    			RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/LoginFail.jsp");
    			dispatcher.forward(request, response);
            }
		} catch (Exception e) {
			// throw new ServletException(e);
			
			// request에 Exception 데이터 보관
			request.setAttribute("error", e);

			//Error.jsp로 위임
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
            dispatcher.forward(request, response);
		}
	    finally {
			try {if(rs != null) rs.close();} catch(Exception e) {}
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
		}
	}

}
