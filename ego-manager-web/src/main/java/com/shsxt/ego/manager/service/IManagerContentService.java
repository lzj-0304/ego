package com.shsxt.ego.manager.service;

import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.model.PageResult;
import com.shsxt.ego.rpc.pojo.TbContent;
import com.shsxt.ego.rpc.query.ContentQuery;

public interface IManagerContentService {
    PageResult<TbContent> loadContentListService(ContentQuery contentQuery);

    EgoResult saveContentService(TbContent tbContent);

    EgoResult deleteContentService(String ids);

    EgoResult updateContentService(TbContent tbContent);
}
