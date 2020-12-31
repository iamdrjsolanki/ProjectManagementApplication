package com.iamdrjsolanki.pma.logging;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggerAspect {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	//pointcut->where, within(packageName.->class.->methods*->everything else)
	@Pointcut("within(com.iamdrjsolanki.pma.controllers..*)")
	public void definePackagePointCuts() {
		//empty method just to name the location specified in the point cut.
	}
	
	//After is the Advice(After, Before, Around, AfterReturning, AfterThrowing) & takes the PointCut methods as arguments
//	@After("definePackagePointCuts()")
//	public void logAfter(JoinPoint jp) {
//		log.debug("******After Method Execution******* \n {}, {} () with arguments = {}", 
//					jp.getSignature().getDeclaringTypeName(), 
//					jp.getSignature().getName(), 
//					Arrays.toString(jp.getArgs())
//				);
//		log.debug("***********************************\n");
//	}
	
	@Around("definePackagePointCuts()")
	public Object logAround(ProceedingJoinPoint jp) {
		log.debug("******Before Method Execution******* \n {}, {} () with arguments = {}", 
					jp.getSignature().getDeclaringTypeName(), 
					jp.getSignature().getName(), 
					Arrays.toString(jp.getArgs())
				);
		log.debug("***********************************\n");
		
		Object o=null;
		try {
			o = jp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug("******After Method Execution******* \n {}, {} () with arguments = {}", 
				jp.getSignature().getDeclaringTypeName(), 
				jp.getSignature().getName(), 
				Arrays.toString(jp.getArgs())
			);
		log.debug("***********************************\n");
		return o;
	}

}
