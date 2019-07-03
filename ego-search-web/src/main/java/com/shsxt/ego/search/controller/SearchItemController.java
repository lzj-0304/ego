package com.shsxt.ego.search.controller;

import com.shsxt.ego.rpc.pojo.TbItem;
import com.shsxt.ego.search.service.ISearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchItemController {

	@Autowired
	private ISearchItemService searchItemService;

	/****
	 * 处理加载商品基本信息的请求
	 */
	@RequestMapping("/item/{id}")
	public String loadItem(@PathVariable Long id ,Model model){
		TbItem item = searchItemService.loadItemService(id);
		model.addAttribute("item",item);
		
		return "item";
	}
	
}
