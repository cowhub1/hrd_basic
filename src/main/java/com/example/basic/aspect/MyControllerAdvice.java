package com.example.basic.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class MyControllerAdvice {
@ExceptionHandler
// @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
// @ResponseBody
public String handle(Exception e, WebRequest request) {
String message = e.getMessage();
log.debug(message);
// return "<h1>" + message + "</h1>";
return "redirect:/auth/signin";
}
}
