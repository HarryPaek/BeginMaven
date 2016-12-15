package exam.test14;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("exam/test14/beans.xml");

		System.out.println("[Singleton 방식(기본값)] -----------------------------------------------------");
		Engine e1 = ctx.getBean("hyEngine", Engine.class);
		Engine e2 = ctx.getBean("hyEngine", Engine.class);
		
		System.out.printf("engine1 = [%s]\n", e1);
		System.out.printf("engine2 = [%s]\n", e2);
		
		if(e1 == e2)
			System.out.println("engine1 == engine2");

		System.out.println("[Prototype 방식] -----------------------------------------------------");
		Engine e3 = ctx.getBean("kiaEngine", Engine.class);
		Engine e4 = ctx.getBean("kiaEngine", Engine.class);
		
		System.out.printf("engine3 = [%s]\n", e3);
		System.out.printf("engine4 = [%s]\n", e4);

		if(e3 != e4)
			System.out.println("engine3 != engine4");
		
		ctx.close();
	}
}
