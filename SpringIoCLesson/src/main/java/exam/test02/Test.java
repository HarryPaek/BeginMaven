package exam.test02;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("exam/test02/beans.xml");
		
		System.out.println("[컨테이너에 보관된 객체 이름 출력]");
		for(String name : ctx.getBeanDefinitionNames()) {
			System.out.println(name);
		}
		
		System.out.println("[score2의 별명 출력]");
		for(String alias : ctx.getAliases("score2")) {
			System.out.println(alias);
		}
		
		Score score = ctx.getBean("score", Score.class);
		
		Score score2 = ctx.getBean("score2", Score.class);
		Score score3 = ctx.getBean("score3", Score.class);
		Score score4 = ctx.getBean("score4", Score.class);
		
		Score scoreOk = ctx.getBean("score-ok!", Score.class);
		
		System.out.println("[빈 꺼내기]");
		if(score != null) System.out.println("score 찾았음.");
		if(score2 != null) System.out.println("score2 찾았음.");
		if(score3 != null) System.out.println("score3 찾았음.");
		if(score4 != null) System.out.println("score4 찾았음.");
		if(scoreOk != null) System.out.println("score-ok! 찾았음.");

		System.out.println("[생성된 빈 비교]");
		if(score == score2) System.out.println("score == score2");
		if(score2 == score3) System.out.println("score2 == score3");
		if(score3 == score4) System.out.println("score3 == score4");
		if(score4 == scoreOk) System.out.println("score4 == scoreOk");
		
		ctx.close();
	}
}
