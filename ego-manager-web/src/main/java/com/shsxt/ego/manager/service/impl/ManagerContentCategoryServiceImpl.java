package com.shsxt.ego.manager.service.impl;

import com.shsxt.ego.manager.service.IManagerContentCategoryService;
import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.rpc.dto.TreeDto;
import com.shsxt.ego.rpc.pojo.TbContentCategory;
import com.shsxt.ego.rpc.service.IContentCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ManagerContentCategoryServiceImpl implements IManagerContentCategoryService {
    @Resource
    private IContentCategoryService contentCategoryServiceProxy;

    @Override
    public List<TreeDto> queryContentCategoryByPid(Long id) {
        return contentCategoryServiceProxy.queryContentCategoryByPid(id);
    }

    @Override
    public EgoResult saveContentCategoryService(TbContentCategory contentCategory) {
        //创建Date对象
        Date date=new Date();
        contentCategory.setCreated(date);
        contentCategory.setUpdated(date);
        contentCategory.setStatus(1);
        contentCategory.setSortOrder(1);
        contentCategory.setIsParent(false);
        return contentCategoryServiceProxy.saveTbContentCateGory(contentCategory);
    }

    @Override
    public EgoResult deleteContentCategoryService(Long id) {
        return contentCategoryServiceProxy.deleteContentCategoryService(id);
    }
}
