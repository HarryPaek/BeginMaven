package spms.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.abstracts.IController;
import spms.abstracts.IDataBinding;
import spms.bind.ServletRequestDataBinder;
import spms.context.ApplicationContext;
import spms.listeners.ContextLoaderListener;

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
			ApplicationContext ctx = ContextLoaderListener.getApplicationContext();
			
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("session", request.getSession());
			
			IController pageController = (IController) ctx.getBeans(servletPath);
			
			if(pageController == null)
				throw new Exception("요청한 서비스를 찾을 수 없습니다.");
			
			if(pageController instanceof IDataBinding)
				prepareRequestData(request, model, (IDataBinding)pageController);
			
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

	private void prepareRequestData(HttpServletRequest request, HashMap<String, Object> model, IDataBinding dataBinding) throws Exception {
		Map<String, Class<?>> dataBinders = dataBinding.getDataBinders();
		
		for (String dataName : dataBinders.keySet()) {
			model.put(dataName, ServletRequestDataBinder.bind(request, dataBinders.get(dataName), dataName));
		}
	}
}
