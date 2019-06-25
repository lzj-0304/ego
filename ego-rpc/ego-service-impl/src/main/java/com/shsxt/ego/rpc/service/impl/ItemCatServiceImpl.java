package com.shsxt.ego.rpc.service.impl;

import com.shsxt.ego.rpc.dto.TreeDto;
import com.shsxt.ego.rpc.manager.db.dao.TbItemCatMapper;
import com.shsxt.ego.rpc.service.IItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCatServiceImpl implements IItemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Override
    public List<TreeDto> queryAllItemCat() {
        return itemCatMapper.queryAllItemCat();
    }
}