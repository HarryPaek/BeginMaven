package spms.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.abstracts.IController;
import spms.vo.Member;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String servletPath = request.getServletPath();
		
		try {
			ServletContext sc = this.getServletContext();
			
			HashMap<String, Object> model = new HashMap<String, Object>();
			
			IController pageController = (IController) sc.getAttribute(servletPath);
			
			if("/member/add.do".equals(servletPath)) {
				if(request.getParameter("email") != null) {
					model.put("member", new Member()
							.setEmail(request.getParameter("email"))
							.setPassword(request.getParameter("password"))
							.setName(request.getParameter("name")));
				}
			}
			else if("/member/update.do".equals(servletPath)) {
				if(request.getParameter("email") == null) {
					model.put("member", new Member().setNo(Integer.parseInt(request.getParameter("no"))));
				}
				else {
					model.put("member", new Member()
							.setNo(Integer.parseInt(request.getParameter("no")))
							.setEmail(request.getParameter("email"))
							.setName(request.getParameter("name")));
				}
			}
			else if("/member/delete.do".equals(servletPath)) {
				model.put("member", new Member().setNo(Integer.parseInt(request.getParameter("no"))));
			}
			else if("/auth/login.do".equals(servletPath)) {
				if(request.getParameter("email") != null) {
					model.put("session", request.getSession());
					model.put("member", new Member()
							.setEmail(request.getParameter("email"))
							.setPassword(request.getParameter("password")));
				}
			}
			else if("/auth/logout.do".equals(servletPath)) {
				model.put("session", request.getSession());
			}
			
			String viewUrl = pageController.execute(model);
				
			for(String key : model.keySet()) {
				if(!key.equals("session")) 
					request.setAttribute(key, model.get(key));
			}
			
			if(viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(viewUrl);
				dispatcher.include(request, response);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request, response);
		}
	}
}
