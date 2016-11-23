package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
	    try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection("jdbc:mysql://localhost/studydb", "study", "study");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select MNO, MNAME, EMAIL, CRE_DATE from MEMBERS order by MNO ASC");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head><title>회원목록</title></head>");
			out.println("<body>");
			out.println("<h1>회원목록</h1>");
			out.println("<p><a href='add'>신규 회원</a></p>");
			while (rs.next()) {
				out.format("%1$d, <a href='update?no=%1$d'>%2$s</a>, %3$s, %4$tF %4$tT<br>", rs.getInt("MNO"), rs.getString("MNAME"), rs.getString("EMAIL"), rs.getTimestamp("CRE_DATE"));
			}
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
}
