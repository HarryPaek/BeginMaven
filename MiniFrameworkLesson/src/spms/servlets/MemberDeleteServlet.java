package spms.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MySqlMemberDao;

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
	    try {
	    	ServletContext sc = this.getServletContext();
	    	MySqlMemberDao dao = (MySqlMemberDao)sc.getAttribute("memberDao");
	    	
	    	dao.delete(Integer.parseInt(request.getParameter("no")));
			
			// Set View Url
			request.setAttribute("viewUrl", "MemberDeleteSuccess.jsp");
		}
	    catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
