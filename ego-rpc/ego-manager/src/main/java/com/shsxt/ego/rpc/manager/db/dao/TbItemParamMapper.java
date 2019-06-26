package com.shsxt.ego.rpc.manager.db.dao;

import com.shsxt.ego.rpc.pojo.TbItemParam;
import com.shsxt.ego.rpc.query.ItemParamQuery;

import java.util.List;

public interface TbItemParamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbItemParam record);

    int insertSelective(TbItemParam record);

    TbItemParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbItemParam record);

    int updateByPrimaryKeyWithBLOBs(TbItemParam record);

    int updateByPrimaryKey(TbItemParam record);

    List<TbItemParam> itemParamListByParams(ItemParamQuery itemParamQuery);

    Integer deleteItemParam(List<Long> asList);

    TbItemParam loadItemParamByCid(Long cid);
}