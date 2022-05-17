package och06_AOP3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAop{
	
	@Pointcut("within(och06_AOP3.*)")	//och06_AOP3.aop3 패키지 안에 있는 모든 메소드
	private void pointcutMethod() {
		
	}
	
	@Around("pointcutMethod()")
	public Object loggerAop(ProceedingJoinPoint joinPoint) throws Throwable {
		// 핵심업무에 사용 method
		String signatureStr = joinPoint.getSignature().toString();
		long st = System.currentTimeMillis();
		System.out.println(signatureStr + " is start....");
		
		try {
			// 핵심업무 수행 ---> student.getStudentInfo
			Object obj = joinPoint.proceed();
			return obj;
		} finally {
			long et = System.currentTimeMillis();
			System.out.println(signatureStr + " is finished.");
			System.out.println(signatureStr + " 경과시간 : " + (et - st));
		}
	}
	
	@Before("within(och06_AOP3.buz.*)")
	public void beforeAdvice() {
		System.out.println("beforeAdvice()");
	}
	
	@After("within(och06_AOP3.buz.*)")
	public void afterAdvice() {
		System.out.println("@AfterAdvice()");
	}
}