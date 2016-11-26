package spms.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/member/dao/list")
public class DaoMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	    	ServletContext sc = this.getServletContext();
	    	MemberDao dao = (MemberDao)sc.getAttribute("memberDao");
			
			// request에 회원 목록 데이터를 보관
			request.setAttribute("members", dao.selectList());
			
			response.setContentType("text/html; charset=UTF-8");
			//JSP로 출력을 위임
			RequestDispatcher dispatcher = request.getRequestDispatcher("/member/jstl/MemberList.jsp");
			dispatcher.include(request, response);
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
