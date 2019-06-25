package com.shsxt.ego.manager.service.impl;

import com.shsxt.ego.manager.service.IManagerItemService;
import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.model.PageResult;
import com.shsxt.ego.rpc.pojo.TbItem;
import com.shsxt.ego.rpc.query.ItemQuery;
import com.shsxt.ego.rpc.service.IItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


@Service
public class ManagerItemServiceImpl implements IManagerItemService {
    @Resource
    private IItemService itemServiceProxy;

    @Override
    public PageResult<TbItem> queryItemsByParams(ItemQuery itemQuery) {
        return itemServiceProxy.queryItemsByParams(itemQuery);
    }

    @Override
    public EgoResult shelfItem(Long[] ids) {
        //将ids数组转化为List集合
        List<Long> itemIds = Arrays.asList(ids);
        //调用远程服务
        return itemServiceProxy.updateItemStatus(itemIds, 1);
    }

    @Override
    public EgoResult instockItem(Long[] ids) {
        //将ids数组转化为List集合
        List<Long> itemIds = Arrays.asList(ids);
        return itemServiceProxy.updateItemStatus(itemIds,2);
    }

    @Override
    public EgoResult itemDelete(Long[] ids) {
        List<Long> itemIds = Arrays.asList(ids);
        //调用远程删除商品信息的服务
        return itemServiceProxy.updateItemStatus(itemIds,3);
    }
}
