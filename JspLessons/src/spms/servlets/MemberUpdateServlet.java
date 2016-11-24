package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
	    try {
	    	ServletContext sc = this.getServletContext();
	    	conn = (Connection)sc.getAttribute("conn");

	        stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format("select MNO, MNAME, EMAIL, CRE_DATE from MEMBERS where MNO = %s;", request.getParameter("no")));

			response.setContentType("text/html; charset=UTF-8");
			Member member = new Member();

			if(rs.next())
				member.setNo(rs.getInt("MNO"))
				      .setName(rs.getString("MNAME"))
				      .setEmail(rs.getString("EMAIL"))
				      .setCreatedDate(rs.getTimestamp("CRE_DATE"));
			
			// request에 회원 데이터를 보관
			request.setAttribute("member", member);
			
			//JSP로 출력을 위임
			RequestDispatcher dispatcher = request.getRequestDispatcher("/member/MemberUpdateForm.jsp");
			dispatcher.include(request, response);
		} catch (Exception e) {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
	    try {
	    	ServletContext sc = this.getServletContext();
	    	conn = (Connection)sc.getAttribute("conn");

	    	stmt = conn.prepareStatement("UPDATE MEMBERS SET MNAME=?, EMAIL=?, MOD_DATE=now() WHERE MNO = ?;");
			
			stmt.setString(1, request.getParameter("name"));
			stmt.setString(2, request.getParameter("email"));
			stmt.setInt(3, Integer.parseInt(request.getParameter("no")));
			stmt.executeUpdate();
			
			response.sendRedirect("list");
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
