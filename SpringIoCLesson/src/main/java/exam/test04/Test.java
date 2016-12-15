package exam.test04;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("exam/test04/beans.xml");
		
		Score score1 = ctx.getBean("score1", Score.class);
		System.out.printf("score1 = [%s]\n", score1);

		Score score2 = ctx.getBean("score2", Score.class);
		System.out.printf("score1 = [%s]\n", score2);

		Score score3 = ctx.getBean("score3", Score.class);
		System.out.printf("score1 = [%s]\n", score3);

		Score score4 = ctx.getBean("score4", Score.class);
		System.out.printf("score1 = [%s]\n", score4);

		Score score5 = ctx.getBean("score5", Score.class);
		System.out.printf("score1 = [%s]\n", score5);
		
		ctx.close();
	}
}
