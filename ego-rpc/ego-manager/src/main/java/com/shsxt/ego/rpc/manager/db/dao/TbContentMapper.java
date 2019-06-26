package com.shsxt.ego.rpc.manager.db.dao;

import com.shsxt.ego.rpc.pojo.TbContent;

public interface TbContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbContent record);

    int insertSelective(TbContent record);

    TbContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbContent record);

    int updateByPrimaryKeyWithBLOBs(TbContent record);

    int updateByPrimaryKey(TbContent record);
}