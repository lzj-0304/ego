package com.shsxt.ego.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

	@RequestMapping("/{url}")
	public String loadPage(@PathVariable String url, @RequestParam(required=false)String redirect,
			Model model){
		model.addAttribute("redirect", redirect);
		return url;
	}
}
