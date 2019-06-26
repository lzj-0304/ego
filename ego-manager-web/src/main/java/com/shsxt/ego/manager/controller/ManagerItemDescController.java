package com.shsxt.ego.manager.controller;

import com.shsxt.ego.manager.service.IManagerItemDescService;
import com.shsxt.ego.model.EgoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ManagerItemDescController {

	//注入service对象
	@Autowired
	private IManagerItemDescService managerItemDescService;
	
	/**
	 * 处理加载商品描述信息的请求
	 * **/
	@RequestMapping(value="item/desc/{itemId}")
	@ResponseBody
	public EgoResult itemDesc(@PathVariable Long itemId){
		return managerItemDescService.getItemDescService(itemId);
		
	}
}
