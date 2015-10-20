package com.rw.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.api.config.ApiApplication;

@Configuration
public class Initializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		return application.sources(Application.class);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.scan("com.api");
		applicationContext.register(ApiApplication.class);
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		dispatcherServlet.setApplicationContext(applicationContext);
		
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("ApiDispatcherServlet", dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/api/*");

		super.onStartup(servletContext);
	}

	@Bean
	DispatcherServlet webDispatcherServlet() {
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		return dispatcherServlet;
	}

	/**
	 * Register dispatcherServlet programmatically
	 * 
	 * @return ServletRegistrationBean
	 */
	@Bean
	public ServletRegistrationBean dispatcherServletRegistration() {

		ServletRegistrationBean registration = new ServletRegistrationBean(
				webDispatcherServlet(), "/");
		registration.setLoadOnStartup(1);
		//registration.setName("webDispatcherServletRegistration");

		return registration;
	}

	
	// @Bean
	// public FilterRegistrationBean filterRegistrationBean () {
	//
	// FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	//
	// return registrationBean;
	// }
}