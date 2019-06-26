package com.shsxt.ego.manager.service.impl;

import com.shsxt.ego.manager.service.IManagerItemDescService;
import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.rpc.pojo.TbItemDesc;
import com.shsxt.ego.rpc.service.IItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerItemDescServiceImpl implements IManagerItemDescService {

	//注入远程服务的代理对象
	@Autowired
	private IItemDescService itemDescServiceProxy;
	@Override
	public EgoResult getItemDescService(Long itemId) {
		EgoResult egoResult =new EgoResult();
		TbItemDesc itemDesc = itemDescServiceProxy.queryItemDescByItemId(itemId);
		egoResult.setData(itemDesc);
		return egoResult;
	}

}
