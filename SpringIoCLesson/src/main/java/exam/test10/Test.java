package exam.test10;

import java.util.Map.Entry;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("exam/test10/beans.xml");
		
		Car car1 = ctx.getBean("car1", Car.class);
		System.out.printf("car1 = [%s]\n", car1);
		
		Car car2 = ctx.getBean("car2", Car.class);
		System.out.printf("car2 = [%s]\n", car2);
		
		System.out.println("[Properties 타입] -----------------------------------------------------");
	    Tire spareTire = ctx.getBean("spareTire", Tire.class);
	    
	    for (Entry<Object, Object> entry : spareTire.getSpec().entrySet()) {
	    	System.out.printf("%s:%s\n", entry.getKey(), entry.getValue());
		}

		System.out.println("[Map 타입] -----------------------------------------------------");
    
	    for (Entry<String, Object> entry : car1.getOptions().entrySet()) {
	    	System.out.printf("%s:%s\n", entry.getKey(), entry.getValue());
		}

		ctx.close();
	}
}
