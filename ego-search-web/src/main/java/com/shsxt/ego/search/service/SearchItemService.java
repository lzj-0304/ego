package com.shsxt.ego.search.service;

import com.shsxt.ego.rpc.pojo.TbItem;

public interface SearchItemService {


	/****
	 * 查询商品基本信息
	 * @param id
	 * @return
	 */
	public TbItem loadItemService(Long id);
}
