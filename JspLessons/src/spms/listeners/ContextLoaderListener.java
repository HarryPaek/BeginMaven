/**
 * 
 */
package spms.listeners;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import spms.dao.MemberDao;

/**
 * @author HarryPaek
 *
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {
	Connection conn;

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
	    	
			MemberDao dao = new MemberDao();
			dao.setDataSource(ds);
			
			conn = ds.getConnection();
			sc.setAttribute("conn", conn);
			sc.setAttribute("memberDao", dao);
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
		try {
			if(conn != null && conn.isClosed() == false)
				conn.close();
		}
    	catch (SQLException e) {}
	}
}
