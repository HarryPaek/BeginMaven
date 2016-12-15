package exam.test05;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("exam/test05/beans.xml");
		
		Score score1 = ctx.getBean("score1", Score.class);
		System.out.printf("score1 = [%s]\n", score1);

		Score score2 = ctx.getBean("score2", Score.class);
		System.out.printf("score1 = [%s]\n", score2);
		
		ctx.close();
	}
}
