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

import spms.dao.MemberDao;
import spms.vo.Member;

/**
 * Servlet implementation class MemberAddServlet
 */
@WebServlet("/member/dao/add")
public class DaoMemberAddServlet extends HttpServlet {
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
		
	    try {
	    	ServletContext sc = this.getServletContext();
	    	conn = (Connection)sc.getAttribute("conn");
	    	
	    	MemberDao dao = new MemberDao();
	    	dao.setConnecion(conn);

	    	dao.insert(new Member().setName(request.getParameter("name"))
	    			               .setEmail(request.getParameter("email"))
	    			               .setPassword(request.getParameter("password")));
			
	    	response.setContentType("text/html; charset=UTF-8");
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/member/MemberAddSuccess.jsp");
			dispatcher.forward(request, response);
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
