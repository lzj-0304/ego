package com.shsxt.ego.portal.service.impl;

import com.shsxt.ego.model.BigPicture;
import com.shsxt.ego.portal.service.IPortalContentService;
import com.shsxt.ego.rpc.pojo.TbContent;
import com.shsxt.ego.rpc.service.IContentService;
import com.shsxt.ego.utils.JsonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PortalContentServiceImpl implements IPortalContentService {
    @Resource
    private IContentService contentServiceProxy;
    @Override
    public String loadContentListByCidService(Long categoryId) {
        //调用远程服务
        List<TbContent> list = contentServiceProxy.loadTbContentListByCidService(categoryId);
        //封装前台数据格式的广告数据
        List<BigPicture> bigList=new ArrayList<>();
        for(TbContent content:list){
            BigPicture pic = new BigPicture();
            pic.setSrcb(content.getPic());pic.setHeight(240);
            pic.setAlt(content.getTitle());
            pic.setWidth(670);
            pic.setSrc(content.getPic2());
            pic.setWidthb(550);
            pic.setHref(content.getUrl());
            pic.setHeightb(240);
            bigList.add(pic);
        }
        //将符合前提数据格式规范的 list 集合，序列化为 json 字符串
        String str = JsonUtils.objectToJson(bigList);
        return str;
    }
}
