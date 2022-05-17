package och06_AOP2;

import org.aspectj.lang.ProceedingJoinPoint;

//횡단관심사 역할 
//Performance 측정
public class LogAop {
	// Around Advice에서 사용할 공통기능 메서드
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
	
	public void beforeAdvice() {
		System.out.println("beforeAdvice()");
	}
	
	public void afterReturningAdvice() {
		System.out.println("afterReturningAdvice()");
	}
	
	public void afterThrowingAdvice() {
		System.out.println("afterThrowingAdvice()");
	}
	
	public void afterAdvice() {
		System.out.println("afterAdvice()");
	}
}
