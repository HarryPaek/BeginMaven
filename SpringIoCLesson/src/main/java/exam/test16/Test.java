package exam.test16;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("exam/test16/beans.xml");

		System.out.println("[Custom Editor 활용]-----------------------");
		Tire t1 = ctx.getBean("hankookTire", Tire.class);
		Tire t2 = ctx.getBean("kumhoTire", Tire.class);
		
		System.out.printf("tire1 = [%s]\n", t1);
		System.out.printf("tire2 = [%s]\n", t2);
		
		ctx.close();
	}
}
