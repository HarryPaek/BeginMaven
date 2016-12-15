package exam.test01;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("exam/test01/beans.xml");
		Score score = ctx.getBean("score", Score.class);
		
		System.out.printf("합계: %f\n", score.sum());
		System.out.printf("평균: %f\n", score.average());
		
		ctx.close();
	}
}
