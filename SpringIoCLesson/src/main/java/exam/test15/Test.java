package exam.test15;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("exam/test15/beans.xml");

		System.out.println("[날짜 형식 지정을 위한 인스턴스 팩토리 Method 활용]-----------------------");
		Tire t1 = ctx.getBean("hankookTire", Tire.class);
		Tire t2 = ctx.getBean("kumhoTire", Tire.class);
		
		System.out.printf("tire1 = [%s]\n", t1);
		System.out.printf("tire2 = [%s]\n", t2);
		
		ctx.close();
	}
}
