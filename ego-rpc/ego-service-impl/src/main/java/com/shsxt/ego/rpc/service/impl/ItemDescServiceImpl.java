package com.shsxt.ego.rpc.service.impl;

import com.shsxt.ego.rpc.manager.db.dao.TbItemDescMapper;
import com.shsxt.ego.rpc.pojo.TbItemDesc;
import com.shsxt.ego.rpc.service.IItemDescService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ItemDescServiceImpl implements IItemDescService {
    @Resource
    private TbItemDescMapper itemDescMapper;
    @Override
    public TbItemDesc queryByItemId(Long itemId) {
        return itemDescMapper.selectByPrimaryKey(itemId);
    }
}
