package spms.listeners;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import spms.abstracts.IMemberDao;
import spms.controllers.LoginController;
import spms.controllers.LogoutController;
import spms.controllers.MemberAddController;
import spms.controllers.MemberDeleteController;
import spms.controllers.MemberListController;
import spms.controllers.MemberUpdateController;
import spms.dao.MySqlMemberDao;

/**
 * Application Lifecycle Listener implementation class ContextLoaderListener
 *
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("ContextLoaderListener contextInitialized()...");
		try {
	    	ServletContext sc = event.getServletContext();
	    	
	    	InitialContext initialContext = new InitialContext();
	    	DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/studydb");
	    	
			IMemberDao dao = new MySqlMemberDao();
			dao.setDataSource(ds);
			
			sc.setAttribute("/auth/login.do", new LoginController().setMemberDao(dao));
			sc.setAttribute("/auth/logout.do", new LogoutController());
			sc.setAttribute("/member/list.do", new MemberListController().setMemberDao(dao));
			sc.setAttribute("/member/add.do", new MemberAddController().setMemberDao(dao));
			sc.setAttribute("/member/update.do", new MemberUpdateController().setMemberDao(dao));
			sc.setAttribute("/member/delete.do", new MemberDeleteController().setMemberDao(dao));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("ContextLoaderListener contextDestroyed()...");
	}
}