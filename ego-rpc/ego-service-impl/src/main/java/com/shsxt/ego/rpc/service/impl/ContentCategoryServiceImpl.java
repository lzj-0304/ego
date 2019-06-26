package com.shsxt.ego.rpc.service.impl;

import com.shsxt.ego.rpc.dto.TreeDto;
import com.shsxt.ego.rpc.manager.db.dao.TbContentCategoryMapper;
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
}
