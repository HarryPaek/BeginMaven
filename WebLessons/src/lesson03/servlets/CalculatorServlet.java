/**
 * 
 */
package lesson03.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * @author HomeUser
 *
 */
@SuppressWarnings("serial")
@WebServlet(name="Calculator", urlPatterns={"/lesson03/calculator", "/lesson03/calc"})
public class CalculatorServlet extends GenericServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int a = Integer.parseInt(request.getParameter("a"));
		int b = Integer.parseInt(request.getParameter("b"));
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		writer.format("a = %1$d, b = %2$d의 계산결과 입니다.\n", a, b);
		writer.format("a + b = %1$d\n", a + b);
		writer.format("a - b = %1$d\n", a - b);
		writer.format("a * b = %1$d\n", a * b);
		writer.format("a / b = %1$f\n", (float)a / (float)b);
		writer.format("a %% b = %1$d\n", (a % b));
	}
}
