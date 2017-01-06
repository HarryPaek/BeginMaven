/**
 * 
 */
package net.foundation.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author HarryPaek
 *
 */
@Component
@Aspect
public class SampleAdvice {
	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);
	
/*
	@Before("execution(* net.foundation.service.MessageService*.*(..))")
	public void startLog(JoinPoint jp) {
		logger.info("===== ===== ===== %%%%% %%%%% %%%%% ##### %%%%% %%%%% %%%%% ===== ===== =====");
		logger.info(Arrays.toString(jp.getArgs()));
		logger.info("===== ===== ===== %%%%% %%%%% %%%%% ##### %%%%% %%%%% %%%%% ===== ===== =====");
	}
*/	

	@Around("execution(* net.foundation.service.*Service*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("===== ===== ===== %%%%% %%%%% %%%%% ##### %%%%% %%%%% %%%%% ===== ===== =====");
		long startTime = System.currentTimeMillis();
		
		logger.info(Arrays.toString(pjp.getArgs()));
		Object result = pjp.proceed();
		
		long endTime = System.currentTimeMillis();
		logger.info(pjp.getSignature().getName() + ": " + (endTime - startTime));
		logger.info("===== ===== ===== %%%%% %%%%% %%%%% ##### %%%%% %%%%% %%%%% ===== ===== =====");
		
		return result;
	}
}
