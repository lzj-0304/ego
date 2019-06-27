package com.shsxt.ego.manager.service.impl;

import com.shsxt.ego.manager.service.IManagerContentService;
import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.model.PageResult;
import com.shsxt.ego.rpc.pojo.TbContent;
import com.shsxt.ego.rpc.query.ContentQuery;
import com.shsxt.ego.rpc.service.IContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ManagerContentServiceImpl implements IManagerContentService {
    @Resource
    private IContentService contentServiceProxy;
    @Override
    public PageResult<TbContent> loadContentListService(ContentQuery contentQuery) {
        return contentServiceProxy.loadContentListService(contentQuery);
    }

    @Override
    public EgoResult saveContentService(TbContent tbContent) {
        Date date=new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        return contentServiceProxy.saveContentService(tbContent);
    }

    @Override
    public EgoResult deleteContentService(String ids) {
        // TODO Auto-generated method stub
        String[] idss=ids.split(",");
        //将idss转化为List<Long>

        //创建List集合对象
        List<Long> list = new ArrayList<>();

        for(String id:idss){
            list.add(Long.parseLong(id));
        }
        return contentServiceProxy.deleteContentService(list);
    }

    @Override
    public EgoResult updateContentService(TbContent tbContent) {
        Date date = new Date();
        tbContent.setUpdated(date);
        return contentServiceProxy.updateContentService(tbContent);
    }
}
