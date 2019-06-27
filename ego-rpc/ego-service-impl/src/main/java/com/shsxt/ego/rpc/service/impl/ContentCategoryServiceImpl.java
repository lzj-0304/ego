package com.shsxt.ego.rpc.service.impl;

import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.rpc.dto.TreeDto;
import com.shsxt.ego.rpc.manager.db.dao.TbContentCategoryMapper;
import com.shsxt.ego.rpc.pojo.TbContentCategory;
import com.shsxt.ego.rpc.service.IContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentCategoryServiceImpl implements IContentCategoryService {
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<TreeDto> queryContentCategoryByPid(Long id) {
        return contentCategoryMapper.queryContentCategoryByPid(id);
    }

    @Override
    public EgoResult saveTbContentCateGory(TbContentCategory contentCategory) {
        EgoResult result = new EgoResult();
        //TbContentCategory对象
        TbContentCategory record = new TbContentCategory();
        record.setIsParent(true);
        record.setId(contentCategory.getParentId());
        //更新当前添加的节点父节点的is_parent
        contentCategoryMapper.updateByPrimaryKeySelective(record);
        //添加内容分类节点
        contentCategoryMapper.insertSelective(contentCategory);
        result.setData(contentCategory);
        return result;
    }

    @Override
    public EgoResult deleteContentCategoryService(Long id) {
        //获得当前点击的节点父节点id
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        Long pid=contentCategory.getParentId();
        //根据pid查询，pid对应的节点是否有子节点
        int count = contentCategoryMapper.countSubCategoryByPid(pid);
        if(count==1){//更新当前需要删除的节点的父节点的is_parent
            TbContentCategory record=new TbContentCategory();
            record.setId(pid);
            record.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKeySelective(record);
        }
        //删除当前点击的节点
        contentCategoryMapper.deleteByPrimaryKey(id);
        return new EgoResult();
    }
}
