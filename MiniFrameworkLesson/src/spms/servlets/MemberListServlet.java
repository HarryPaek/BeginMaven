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
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	    	ServletContext sc = this.getServletContext();
	    	MySqlMemberDao dao = (MySqlMemberDao)sc.getAttribute("memberDao");
			
			// request에 회원 목록 데이터를 보관
			request.setAttribute("members", dao.selectList());
			
			//Set View Url
			request.setAttribute("viewUrl", "/member/MemberList.jsp");
		}
	    catch (Exception e) {
	    	throw new ServletException(e);
		}
	}

}
