package exam.test07;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("exam/test07/beans.xml");
		
		Car car1 = ctx.getBean("car1", Car.class);
		System.out.printf("car1 = [%s]\n", car1);
		
		Engine engine = ctx.getBean("engine1", Engine.class);
		engine.cc = 3000;

		Car car2 = ctx.getBean("car2", Car.class);
		System.out.printf("car2 = [%s]\n", car2);

		Car car3 = ctx.getBean("car3", Car.class);
		System.out.printf("car3 = [%s]\n", car3);

		Car car4 = ctx.getBean("car4", Car.class);
		System.out.printf("car4 = [%s]\n", car4);
		
		ctx.close();
	}
}
