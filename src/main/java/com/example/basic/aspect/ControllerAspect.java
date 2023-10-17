package com.example.basic.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class ControllerAspect {
  //성능 평가(걸린 시간)
  long start = 0L;
  long end = 0L;

  @Before(value = "execution (* com.example.basic.controller.*.*(..))")
  //* execution~은 pointcut,'*'은 모든것 ,package 아래 모든것+모든 클래스+모든 메소드  */
  public void onBeforeHandler(JoinPoint joinPoint) {
    log.warn("@Before run");
    start=System.currentTimeMillis();
  }

  @After(value = "execution (* com.example.basic.controller.*.*(..))")
  public void onAfterHandler(JoinPoint joinPoint) {
    log.warn("@After run");
    end=System.currentTimeMillis();
    log.warn(end-start+"");
    //""더해주는 이유는 warn은 시작변수를 string으로 받기 때문에
  }

  // @AfterReturning(value = "execution (* com.example.basic.controller.*.*(..))", returning = "data")
  // public void onAfterReturningHandler(JoinPoint joinPoint, Object data) {
  //   if (data != null) {
  //     log.debug(data.toString());
  //   }
  //   log.debug("@AfterReturning run");
  // }
@AfterReturning(value = "execution (* com.example.basic.util.*.*(..))", returning = "data")
  public void onAfterReturningHandler(JoinPoint joinPoint, Object data) {
    if (data != null) {
      log.warn(data.toString());
    }
    log.warn("@AfterReturning run");
  }

  
  @After(value = "execution (* com.example.basic.util.*.*(..))")
  public void amugona(JoinPoint joinPoint) {
   String name = joinPoint.getSignature().getName();
    // getSignature()는 아마 대상메소드/ getName()는 메소드 이름 가져오기
    //따라서 auth/signin에서 로그인 하면 encode출력됨(Encrypt안에 메소드 이름)
    log.warn(name);
    
  }

  @AfterThrowing(value ="execution (* com.example.basic.controller.AuthController.*.*(..))")
  public void afterThrowing(JoinPoint joinPoint){
    log.warn("오류 발생");
  }
}
