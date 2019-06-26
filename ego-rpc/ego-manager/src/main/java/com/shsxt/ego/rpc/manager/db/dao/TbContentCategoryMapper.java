package com.shsxt.ego.rpc.manager.db.dao;

import com.shsxt.ego.rpc.dto.TreeDto;
import com.shsxt.ego.rpc.pojo.TbContentCategory;

import java.util.List;

public interface TbContentCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbContentCategory record);

    int insertSelective(TbContentCategory record);

    TbContentCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbContentCategory record);

    int updateByPrimaryKey(TbContentCategory record);

    List<TreeDto> queryContentCategoryByPid(Long id);
}