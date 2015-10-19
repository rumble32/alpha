package com.api.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="apiIndexController")
public class IndexController {

	
	@RequestMapping(value="")
	public String index() {
	
		JSONObject result = new JSONObject();
		result.put("foo", "呵呵");
		
		return result.toString();
	}
}
