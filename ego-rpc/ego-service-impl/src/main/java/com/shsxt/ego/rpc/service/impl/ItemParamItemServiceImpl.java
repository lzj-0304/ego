package com.shsxt.ego.rpc.service.impl;

import com.shsxt.ego.rpc.manager.db.dao.TbItemParamItemMapper;
import com.shsxt.ego.rpc.pojo.TbItemParamItem;
import com.shsxt.ego.rpc.service.IItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemParamItemServiceImpl  implements IItemParamItemService {
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;
    @Override
    public TbItemParamItem queryItemParamItemByItemId(Long itemId) {
        return  itemParamItemMapper.queryItemParamItemByItemId(itemId);
    }
}
