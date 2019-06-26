package com.shsxt.ego.rpc.manager.db.dao;

import com.shsxt.ego.rpc.pojo.TbItem;
import com.shsxt.ego.rpc.query.ItemQuery;

import java.util.List;
import java.util.Map;

public interface TbItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbItem record);

    int insertSelective(TbItem record);

    TbItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);

    List<TbItem> itemListByParams(ItemQuery itemQuery);

    Integer updateItemStatus(Map<String, Object> params);

    Integer itemDelete(List<Long> itemIds);
}