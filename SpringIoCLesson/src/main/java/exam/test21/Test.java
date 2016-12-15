package exam.test21;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("exam/test21/beans.xml");

		System.out.println("[@Component && @Autowired Annotation 활용]-----------------------");
		Car c1 = ctx.getBean("car", Car.class);
		c1.setModel("HyKia Car1");

		Engine e1 = ctx.getBean("engine", Engine.class);
		e1.setMaker("HyKia");
		e1.setCc(1997);
		
		if(c1 != null)
			System.out.println("car1 != null");
		
		if(e1 != null)
			System.out.println("engine1 != null");
		
		Car c2 = ctx.getBean("car", Car.class);
		Engine e2 = ctx.getBean("engine", Engine.class);
		
		if(c1 == c2)
			System.out.println("car1 == car2");
		
		if(e1 == e2)
			System.out.println("engine1 == engine2");

		System.out.printf("car1 = [%s]\n", c1);
		System.out.printf("car2 = [%s]\n", c2);
		
		System.out.printf("engine1 = [%s]\n", e1);
		System.out.printf("engine2 = [%s]\n", e2);

		ctx.close();
	}
}
