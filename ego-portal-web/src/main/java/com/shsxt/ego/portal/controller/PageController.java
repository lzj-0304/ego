package com.shsxt.ego.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	
	/****
	 * 进行页面的调整
	 */
	@RequestMapping("/{url}")
	public String loadPage(@PathVariable String url){
		System.out.println("===============");
		
		return url;
	}
}
