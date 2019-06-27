package com.shsxt.ego.rpc.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.model.PageResult;
import com.shsxt.ego.rpc.manager.db.dao.TbContentMapper;
import com.shsxt.ego.rpc.pojo.TbContent;
import com.shsxt.ego.rpc.query.ContentQuery;
import com.shsxt.ego.rpc.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements IContentService {
    //注入mapper对象
    @Autowired
    private TbContentMapper contentMapper;
    @Override
    public PageResult<TbContent> loadContentListService(ContentQuery contentQuery) {
        //执行分页操作
        Page ps = PageHelper.startPage(contentQuery.getPage(), contentQuery.getRows());
        //执行数据库查询操作
        List<TbContent> list = contentMapper.contentListByParams(contentQuery);
        PageResult<TbContent> result = new PageResult<TbContent>();
        result.setRows(list);
        result.setTotal(ps.getTotal());
        return result;
    }

    @Override
    public EgoResult saveContentService(TbContent tbContent) {
        contentMapper.insertSelective(tbContent);
        return EgoResult.ok();
    }

    @Override
    public EgoResult deleteContentService(List<Long> list) {
        contentMapper.deleteContents(list);
        return EgoResult.ok();
    }

    @Override
    public EgoResult updateContentService(TbContent tbContent) {
        contentMapper.updateByPrimaryKeySelective(tbContent);
        return EgoResult.ok();
    }

    @Override
    public List<TbContent> loadTbContentListByCidService(Long cid) {
        ContentQuery contentQuery =new ContentQuery();
        contentQuery.setCategoryId(cid);
        return contentMapper.contentListByParams(contentQuery);
    }
}
