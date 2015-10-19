package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value="webIndexController")
public class IndexController {

	
	@RequestMapping(value="aa")
	public ModelAndView index() {
	
		System.out.println("web_index_IndexController");
		return new ModelAndView("index");
	}
}
