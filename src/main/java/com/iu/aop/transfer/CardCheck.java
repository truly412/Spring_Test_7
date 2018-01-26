package com.iu.aop.transfer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CardCheck {
	
	@Around("execution(* com.iu.aop.transfer..Transport.*())")
	public Object check(ProceedingJoinPoint join) {
		System.out.println("삑 ------ 탑승합니다.");
		Object object = null;
		try {
			object=join.proceed(); //중간에 join(핵심로직)실행시키겠다 
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("삑 ------ 하차합니다.");
		return object;
	}
	
	@Before("execution(* com.iu.aop.transfer..Transport.*())")
	public void fingerPrint(){
		System.out.println("fingerPrint");
	}

}
