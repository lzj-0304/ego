package com.shsxt.ego.rpc.service;

import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.model.PageResult;
import com.shsxt.ego.rpc.pojo.TbItemParam;
import com.shsxt.ego.rpc.query.ItemParamQuery;

public interface IItemParamService {
    PageResult<TbItemParam> queryItemParamByParams(ItemParamQuery itemParamQuery);

    EgoResult deleteItemParam(Long[] ids);

    EgoResult loadItemParamByCid(Long cid);

    EgoResult saveItemParamService(Long cid, String paramData);
}
