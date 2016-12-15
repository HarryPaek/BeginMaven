package exam.test12;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("exam/test12/beans.xml");
		
		Tire t1 = ctx.getBean("hankookTire", Tire.class);
		Tire t2 = ctx.getBean("kumhoTire", Tire.class);
		Tire t3 = ctx.getBean("hankookTire", Tire.class);
		
		System.out.printf("tire1 = [%s]\n", t1);
		System.out.printf("tire2 = [%s]\n", t2);
		System.out.printf("tire3 = [%s]\n", t3);
		
		if(t1 != t2)
			System.out.println("tire1 != tire2");
		
		if(t1 == t3)
			System.out.println("tire1 == tire3");
		
		ctx.close();
	}
}
