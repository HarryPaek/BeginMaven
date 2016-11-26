package spms.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
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
@WebServlet("/auth/dao/login")
public class DaoLoginServlet extends HttpServlet {
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
		
	    try {
	    	ServletContext sc = this.getServletContext();
	    	conn = (Connection)sc.getAttribute("conn");
	    	
	    	MemberDao dao = new MemberDao();
	    	dao.setConnecion(conn);
	    	
	    	Member member = dao.exist(request.getParameter("email"), request.getParameter("password"));
			
            if(member != null) {
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				
				response.sendRedirect(request.getContextPath() + "/member/dao/list");
			}
            else {
    			//LoginFail.jsp로 출력 위임
    			RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/LoginFail.jsp");
    			dispatcher.forward(request, response);
            }
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
