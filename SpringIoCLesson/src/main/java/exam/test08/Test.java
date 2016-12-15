package exam.test08;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("exam/test08/beans.xml");
		
		Car car1 = ctx.getBean("car1", Car.class);
		System.out.printf("car1 = [%s]\n", car1);
		
		Car car2 = ctx.getBean("car2", Car.class);
		System.out.printf("car2 = [%s]\n", car2);
		
		Engine engine = car1.getEngine();
		engine.cc = 3500;
		
		System.out.printf("car1 = [%s]\n", car1);
		System.out.printf("car2 = [%s]\n", car2);
		
		ctx.close();
	}
}
