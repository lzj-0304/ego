package com.shsxt.ego.manager.service;


import com.shsxt.ego.model.EgoResult;

public interface IManagerItemDescService {
	
	/**
	 * 获得需要回显的商品描述
	 * **/
	public EgoResult getItemDescService(Long itemId);

}
