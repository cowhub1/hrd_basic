package com.example.basic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.basic.interceptor.SignInCheckInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
  @Autowired
  private SignInCheckInterceptor signInCheckInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(signInCheckInterceptor)
    .addPathPatterns("/**")
    .excludePathPatterns("/auth/signin","/auth/signup");
    // addPathPatterns("/**"):모든 주소는 반응 해야해
    //.excludePathPatterns("/auth/signin"); 예외로 두자
    WebMvcConfigurer.super.addInterceptors(registry);
  }
}
