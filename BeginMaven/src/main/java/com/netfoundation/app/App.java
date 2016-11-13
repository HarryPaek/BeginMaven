package com.netfoundation.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.netfoundation.abstracts.IMessageService;

/**
 * @author ChongGeun.Paek
 *
 */
public class App 
{
	@Bean
	IMessageService mockMessageService()
	{
		return new IMessageService() {
			public String getMessage() {
				return "Hello World!";
			}
		};
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"com/netfoundation/app/services.xml"});
		
		MessagePrinter printer = context.getBean(MessagePrinter.class);
		printer.printMessage();
		
		((ConfigurableApplicationContext)context).close();
	}
}
