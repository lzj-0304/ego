package com.shsxt.ego.search.controller;

import com.shsxt.ego.search.entity.SearchResult;
import com.shsxt.ego.search.service.ISearchItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@Controller
public class PageController {

	@Resource
	private ISearchItemService searchItemService;

	@RequestMapping("/{url}")
	public String loadPage(@PathVariable String url, String q, @RequestParam(defaultValue = "1") Integer page, Model model){
		String kws = null;
		try {
			kws = new String(q.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		/*List<Item> itemList =new ArrayList<Item>();
		itemList.add(new Item("20000","海尔（Haier）HM-M209<font color='red'>手机</font> 老人机 老人<font color='red'>手机</font> 老年<font color='red'>手机</font> 直板<font color='red'>手机</font> 红色]","买一送一",10000L,"http://pty9die0h.bkt.clouddn.com/1562052515719.jpg","手机"));
		itemList.add(new Item("200001","百合 C16 CDMA电信 迷你小<font color='red'>手机</font> 老年人<font color='red'>手机</font> 金色","买一送一",10000L,"http://pty9die0h.bkt.clouddn.com/1562067960280.jpg","手机"));
		itemList.add(new Item("2000012","优思 (Uniscope) U6X 电信2G<font color='red'>手机</font> 迷你<font color='red'>手机</font> 白","买一送一",10000L,"http://pty9die0h.bkt.clouddn.com/1562125382343.jpg","手机"));
		itemList.add(new Item("20000123213","博瑞（BROR）G2 移动4G<font color='red'>手机手机</font> 白色","买一送一",10000L,"http://pty9die0h.bkt.clouddn.com/1562133030468.png","手机"));
		itemList.add(new Item("20000123213123","百合 C16 CDMA电信 迷你小<font color='red'>手机</font> 老年人<font color='red'>手机</font> 黑色","买一送一",10000L,"http://pty9die0h.bkt.clouddn.com/1562138772593.jpg","手机"));*/
		SearchResult result= searchItemService.queryItemByKey(kws,page);
		model.addAttribute("query", kws);
		model.addAttribute("itemList", result.getList());
		model.addAttribute("page", page);
		model.addAttribute("maxpage", result.getMaxpage());
		return url;
	}
}
