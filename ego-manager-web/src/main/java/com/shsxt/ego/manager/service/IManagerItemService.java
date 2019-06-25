package com.shsxt.ego.manager.service;

import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.model.PageResult;
import com.shsxt.ego.rpc.pojo.TbItem;
import com.shsxt.ego.rpc.pojo.TbItemDesc;
import com.shsxt.ego.rpc.query.ItemQuery;

public interface IManagerItemService {
    PageResult<TbItem> queryItemsByParams(ItemQuery itemQuery);

    EgoResult shelfItem(Long[] ids);

    EgoResult instockItem(Long[] ids);

    EgoResult itemDelete(Long[] ids);


    EgoResult saveItem(TbItem tbItem);

    TbItemDesc queryItemDescByItemId(Long itemId);

    EgoResult updateItem(TbItem tbItem);
}
