package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		// request.setCharacterEncoding("UTF-8");
		
	    try {
	    	ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"), sc.getInitParameter("password"));
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format("select MNO, MNAME, EMAIL, CRE_DATE from MEMBERS where MNO = %s;", request.getParameter("no")));
			rs.next();
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head><title>회원 정보</title></head>");
			out.println("<body>");
			out.println("<h1>회원 정보</h1>");
			
			out.println("<form action='update' method='post'>");
			out.printf("번호: <input type='text' name='no' value='%s' readonly><br>", request.getParameter("no"));
			out.printf("이름: *<input type='text' name='name' value='%s'><br>", rs.getString("MNAME"));
			out.printf("이메일: <input type='text' name='email' value='%s'><br>", rs.getString("EMAIL"));
			out.printf("가입일: %1$tF %1$tT<br>", rs.getTimestamp("CRE_DATE"));
			out.println("<input type='submit' value='저장'>");
			out.println("<input type='reset' value='취소' onclick='location.href=\"list\"'>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		} catch (Exception e) {
			throw new ServletException(e);
		}
	    finally {
			try {if(rs != null) rs.close();} catch(Exception e) {}
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
			try {if(conn != null) conn.close();} catch(Exception e) {}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		request.setCharacterEncoding("UTF-8");
		
	    try {
	    	ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"), sc.getInitParameter("password"));
			stmt = conn.prepareStatement("UPDATE MEMBERS SET MNAME=?, EMAIL=?, MOD_DATE=now() WHERE MNO = ?;");
			
			stmt.setString(1, request.getParameter("name"));
			stmt.setString(2, request.getParameter("email"));
			stmt.setInt(3, Integer.parseInt(request.getParameter("no")));
			stmt.executeUpdate();
			
			response.sendRedirect("list");
		} catch (Exception e) {
			throw new ServletException(e);
		}
	    finally {
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
			try {if(conn != null) conn.close();} catch(Exception e) {}
		}
	}
}
