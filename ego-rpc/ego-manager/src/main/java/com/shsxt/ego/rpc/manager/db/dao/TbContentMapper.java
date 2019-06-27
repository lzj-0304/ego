package com.shsxt.ego.rpc.manager.db.dao;

import com.shsxt.ego.rpc.pojo.TbContent;
import com.shsxt.ego.rpc.query.ContentQuery;

import java.util.List;

public interface TbContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbContent record);

    int insertSelective(TbContent record);

    TbContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbContent record);

    int updateByPrimaryKeyWithBLOBs(TbContent record);

    int updateByPrimaryKey(TbContent record);

    List<TbContent> contentListByParams(ContentQuery contentQuery);

    Integer deleteContents(List<Long> list);

    List<TbContent> loadTbContentListByCid(Long cid);
}