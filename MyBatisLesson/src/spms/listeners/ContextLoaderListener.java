package spms.listeners;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import spms.context.ApplicationContext;

/**
 * Application Lifecycle Listener implementation class ContextLoaderListener
 *
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {

	static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("ContextLoaderListener contextInitialized()...");
		try {
			applicationContext = new ApplicationContext();
			
			String resource = "spms/dao/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			applicationContext.addBean("sqlSessionFactory", sqlSessionFactory);
			
	    	ServletContext sc = event.getServletContext();
	    	String propertiesPath = sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
	    	
	    	applicationContext.prepareObjectsByProperties(propertiesPath);
	    	applicationContext.prepareObjectsByAnnotation("");
	    	applicationContext.injectDependency();
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