package com.shsxt.ego.manager.service.impl;

import com.shsxt.ego.manager.service.IManagerItemParamItemService;
import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.rpc.service.IItemParamItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ManagerItemParamItemServiceImpl implements IManagerItemParamItemService {
    @Resource
    private IItemParamItemService itemParamItemServiceProxy;
    @Override
    public EgoResult queryItemParamItemByItemId(Long itemId) {
        EgoResult egoResult=new EgoResult();
        egoResult.setData(itemParamItemServiceProxy.queryItemParamItemByItemId(itemId));
        return egoResult;
    }
}
