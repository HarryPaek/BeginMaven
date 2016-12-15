package exam.test09;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("exam/test09/beans.xml");
		
		Car car1 = ctx.getBean("car1", Car.class);
		System.out.printf("car1 = [%s]\n", car1);
		
		Car car2 = ctx.getBean("car2", Car.class);
		System.out.printf("car2 = [%s]\n", car2);
		
		ctx.close();
	}
}
