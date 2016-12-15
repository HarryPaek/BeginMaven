package exam.test03;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("exam/test03/beans.xml");
		
		System.out.println("[컨테이너에 보관된 객체 이름 출력]");
		for(String name : ctx.getBeanDefinitionNames()) {
			System.out.println(name);
		}
		
		System.out.println("[exam.test03.Score#0의 별명 출력]");
		for(String alias : ctx.getAliases("exam.test03.Score#0")) {
			System.out.println(alias);
		}

		System.out.println("[익명 빈 꺼내기]");
		Score score1 = ctx.getBean("exam.test03.Score", Score.class);
		Score score2 = ctx.getBean("exam.test03.Score#0", Score.class);
		if(score1 == score2) System.out.println("score == score#0");
		
		Score score3 = ctx.getBean("exam.test03.Score#1", Score.class);
		if(score1 != score3) System.out.println("score != score#1");
		
		System.out.println("[클래스 타입으로 빈 꺼내기]");
		Score score4 = ctx.getBean(Score.class);                     // 동일 클래스 타입 Bean이 2개 이상 발견되면 오류 발생 
		if(score1 == score4) System.out.println("score1 == score4");
		if(score3 == score4) System.out.println("score3 == score4");
		
		ctx.close();
	}
}
