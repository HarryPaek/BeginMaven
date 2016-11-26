/**
 * 
 */
package spms.listeners;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

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
			Class.forName(sc.getInitParameter("driver"));
			Connection conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"), sc.getInitParameter("password"));
			
			MemberDao dao = new MemberDao();
			dao.setConnecion(conn);
			
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
    	catch (Exception e) {}
    	
//    	Enumeration<Driver> drivers = DriverManager.getDrivers();
//        while (drivers.hasMoreElements()) {
//            Driver driver = drivers.nextElement();
//            try {
//                DriverManager.deregisterDriver(driver);
//                System.out.println(String.format("deregistering jdbc driver: %s", driver));
//            } catch (SQLException e) {
//            	System.out.println(String.format("Error deregistering driver %s", driver));
//            	e.printStackTrace();
//            }
//        }
	}
}
