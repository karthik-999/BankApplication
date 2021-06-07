package com.bankapplication.aspect;


import java.time.Duration;
import java.time.Instant;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AccountControllerAspect {

	Logger logger = LoggerFactory.getLogger("AccountControllerAspect");
	Instant startTime = null;
	Instant stopTime = null;
	
	
	
	@Before("execution(public * com.bankapplication.controller.*Controller.*(..))")
	public void loggerAspectBefore() {
		startTime = Instant.now();
	}
	
	
	@AfterReturning("execution(public * com.bankapplication.controller.*Controller.*(..))")
	public void loggerAspectAfter() {
		stopTime = Instant.now();
		var elapsedTime = Duration.between(startTime, stopTime);
		logger.info("Total Time Execution for this method {} ms", elapsedTime.toMillis());
	}
	
}
