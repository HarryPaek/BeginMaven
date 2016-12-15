package exam.test19;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("exam/test19/beans.xml");

		System.out.println("[@Autowired && @Qualifier Annotation 활용]-----------------------");
		Car c1 = ctx.getBean("car1", Car.class);
		Car c2 = ctx.getBean("car2", Car.class);
		
		System.out.printf("car1 = [%s]\n", c1);
		System.out.printf("car2 = [%s]\n", c2);
		
		ctx.close();
	}
}
