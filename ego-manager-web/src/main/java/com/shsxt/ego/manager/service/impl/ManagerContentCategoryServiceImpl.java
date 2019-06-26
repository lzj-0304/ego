package com.shsxt.ego.manager.service.impl;

import com.shsxt.ego.manager.service.IManagerContentCategoryService;
import com.shsxt.ego.rpc.dto.TreeDto;
import com.shsxt.ego.rpc.service.IContentCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ManagerContentCategoryServiceImpl implements IManagerContentCategoryService {
    @Resource
    private IContentCategoryService contentCategoryServiceProxy;

    @Override
    public List<TreeDto> queryContentCategoryByPid(Long id) {
        return contentCategoryServiceProxy.queryContentCategoryByPid(id);
    }
}
