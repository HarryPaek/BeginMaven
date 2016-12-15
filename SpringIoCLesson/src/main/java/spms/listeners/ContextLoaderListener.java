package spms.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Application Lifecycle Listener implementation class ContextLoaderListener
 *
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {

	static ClassPathXmlApplicationContext applicationContext;
	
	public static ClassPathXmlApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("ContextLoaderListener contextInitialized() with ClassPathXmlApplicationContext ...");
		try {
			applicationContext = new ClassPathXmlApplicationContext("beans.xml");
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
		System.out.println("ContextLoaderListener contextDestroyed() with ClassPathXmlApplicationContext ...");
	}
}