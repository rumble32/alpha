package com.api.config;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ApiGlobalExceptionHandlerController {

	private static final Logger logger = LoggerFactory.getLogger(ApiGlobalExceptionHandlerController.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String handleException(HttpServletRequest request, Exception exception){
				
		logger.info("exception = " + exception.toString());
		JSONObject result = new JSONObject();
		result.put("errcode", "1");
		result.put("msg", "异常错误");
		
		return result.toString();
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseBody
	public String handleNoHandlerFoundException(HttpServletRequest request, NoHandlerFoundException exception) {
		
		JSONObject result = new JSONObject();
		result.put("errcode", HttpStatus.NOT_FOUND);
		result.put("msg", "没有找到该接口");
		
		return result.toString();
	}
}
