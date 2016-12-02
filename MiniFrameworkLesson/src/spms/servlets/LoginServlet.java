package spms.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.dao.MemberDao;
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
		// Set View Url
		request.setAttribute("viewUrl", "/auth/LoginForm.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	    	ServletContext sc = this.getServletContext();
	    	MemberDao dao = (MemberDao)sc.getAttribute("memberDao");
	    	
	    	Member member = dao.exist(request.getParameter("email"), request.getParameter("password"));
			
            if(member != null) {
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				
				request.setAttribute("viewUrl", String.format("redirect:%s/member/list.do", request.getContextPath()));
			}
            else {
            	// Set View Url
        		request.setAttribute("viewUrl", "/auth/LoginFail.jsp");
            }
		}
	    catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
