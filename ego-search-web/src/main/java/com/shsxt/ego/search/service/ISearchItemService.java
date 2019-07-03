package com.shsxt.ego.search.service;

import com.shsxt.ego.rpc.pojo.TbItem;
import com.shsxt.ego.search.entity.SearchResult;

public interface ISearchItemService {


	/****
	 * 查询商品基本信息
	 * @param id
	 * @return
	 */
	public TbItem loadItemService(Long id);

	/**
	 * 关键字搜索
	 * @param key
	 * @param page
	 * @return
	 */
	public SearchResult queryItemByKey(String key,Integer page);
}
