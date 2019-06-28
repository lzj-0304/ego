package com.shsxt.ego.rpc.service;

import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.model.PageResult;
import com.shsxt.ego.rpc.pojo.TbItem;
import com.shsxt.ego.rpc.pojo.TbItemDesc;
import com.shsxt.ego.rpc.pojo.TbItemParamItem;
import com.shsxt.ego.rpc.query.ItemQuery;

import java.util.List;

public interface IItemService {
    /**
     * 多条件查询商品列表信息
     */
    PageResult<TbItem> queryItemsByParams(ItemQuery itemQuery);


    /**
     * 商品上下架 删除
     * @param itemIds
     * @param status
     * @return
     */
    EgoResult updateItemStatus(List<Long> itemIds, Integer status);


    EgoResult saveItem(TbItem tbItem, TbItemDesc tbItemDesc, TbItemParamItem itemParamItem);

    EgoResult updateItem(TbItem tbItem,TbItemDesc tbItemDesc, TbItemParamItem itemParamItem);

    EgoResult itemDelete(List<Long> itemIds);

    TbItem loadItemService(Long itemId);
}
