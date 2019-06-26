package com.shsxt.ego.rpc.manager.db.dao;

import com.shsxt.ego.rpc.dto.TreeDto;
import com.shsxt.ego.rpc.pojo.TbItemCat;

import java.util.List;

public interface TbItemCatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbItemCat record);

    int insertSelective(TbItemCat record);

    TbItemCat selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbItemCat record);

    int updateByPrimaryKey(TbItemCat record);

    public List<TreeDto>  queryItemCatByParentId(Long id);
}