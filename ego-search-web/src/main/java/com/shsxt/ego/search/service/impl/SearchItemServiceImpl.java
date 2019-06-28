package com.shsxt.ego.search.service.impl;

import com.shsxt.ego.rpc.pojo.TbItem;
import com.shsxt.ego.rpc.service.IItemService;
import com.shsxt.ego.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchItemServiceImpl implements SearchItemService {
	//调用远程服务的代理对象
	@Autowired
	private IItemService itemServiceProxy;

	@Override
	public TbItem loadItemService(Long id) {
		return itemServiceProxy.loadItemService(id);
	}

}
