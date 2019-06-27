package com.shsxt.ego.rpc.service;

import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.model.PageResult;
import com.shsxt.ego.rpc.pojo.TbContent;
import com.shsxt.ego.rpc.query.ContentQuery;

import java.util.List;

public interface IContentService {
    PageResult<TbContent> loadContentListService(ContentQuery contentQuery);

    EgoResult saveContentService(TbContent tbContent);

    EgoResult deleteContentService(List<Long> list);

    EgoResult updateContentService(TbContent tbContent);
    List<TbContent> loadTbContentListByCidService(Long cid);
}
