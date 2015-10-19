package com.web.config;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class WebGlobalExceptionHandlerController {

	private static final Logger logger = LoggerFactory.getLogger(WebGlobalExceptionHandlerController.class);
	
	@ExceptionHandler(value = Exception.class)  
	public ModelAndView handleException(HttpServletRequest request, Exception exception) throws Exception {
		
		logger.info("exception = " + exception.toString());
		
		ModelAndView andView = new ModelAndView();
		andView.setViewName("error");
		return andView;
	}
	
	@ExceptionHandler(value = NoHandlerFoundException.class)  
	public ModelAndView handleNoHandlerFoundException(HttpServletRequest request, NoHandlerFoundException exception) throws Exception {
				
		ModelAndView andView = new ModelAndView();
		andView.setViewName("notFound");
		return andView;
	}
	
	
}
