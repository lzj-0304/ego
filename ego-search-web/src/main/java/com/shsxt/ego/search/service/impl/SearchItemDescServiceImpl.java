package com.shsxt.ego.search.service.impl;

import com.shsxt.ego.rpc.pojo.TbItemDesc;
import com.shsxt.ego.rpc.service.IItemDescService;
import com.shsxt.ego.search.service.SearchItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchItemDescServiceImpl implements SearchItemDescService {

	//注入远程服务代理对象
	@Autowired
	private IItemDescService itemDescServiceProxy;
	@Override
	public String loadItemDescService(Long id) {
		// TODO Auto-generated method stub
		TbItemDesc itemDesc = itemDescServiceProxy.queryItemDescByItemId(id);
		return itemDesc.getItemDesc();
	}

}
