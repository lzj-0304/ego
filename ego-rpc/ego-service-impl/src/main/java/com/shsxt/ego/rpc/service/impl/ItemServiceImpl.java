package com.shsxt.ego.rpc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.model.PageResult;
import com.shsxt.ego.rpc.manager.db.dao.TbItemMapper;
import com.shsxt.ego.rpc.pojo.TbItem;
import com.shsxt.ego.rpc.query.ItemQuery;
import com.shsxt.ego.rpc.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private TbItemMapper itemMapper;
    @Override
    public PageResult<TbItem> queryItemsByParams(ItemQuery itemQuery) {
        //执行分页操作
        Page ps = PageHelper.startPage(itemQuery.getPage(), itemQuery.getRows());
        TbItem example=new  TbItem();
        //执行数据库查询操作
        List<TbItem> list = itemMapper.itemListByParams(itemQuery);
        PageResult<TbItem> result = new PageResult<TbItem>();
        result.setRows(list);
        result.setTotal(ps.getTotal());
        return result;
    }

    @Override
    public EgoResult updateItemStatus(List<Long> itemIds, Integer status) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("ids",itemIds);
        params.put("status",status);
        itemMapper.updateItemStatus(params);
        return new EgoResult();
    }




}
