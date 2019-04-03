package com.sxgg.demo.exception;


import org.apache.shiro.authz.AuthorizationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * 异常处理
 * @author Administrator
 *
 */
@ControllerAdvice
public class MyControllerAdvice {
	
	  @ExceptionHandler(value = Exception.class) 
	  public String errorHandler(Exception e,Model model) { 
		  System.err.println(e.getMessage());
		      model.addAttribute("error", e.getMessage()); 
		      return "/error/500"; 
		 
	  }
	 
	
	
	@ExceptionHandler(value = AuthorizationException.class)
	public Object errorAuthorizationException(Exception e,Model model) {
		model.addAttribute("error", "权限异常"+e.getMessage()); 
		return "/error/500";

	}
	 
	
	
}
