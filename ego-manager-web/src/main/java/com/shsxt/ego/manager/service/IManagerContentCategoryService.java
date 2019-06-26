package com.shsxt.ego.manager.service;

import com.shsxt.ego.model.PageResult;
import com.shsxt.ego.rpc.dto.TreeDto;
import com.shsxt.ego.rpc.pojo.TbItem;
import com.shsxt.ego.rpc.query.ContentCategoryQuery;

import java.util.List;

public interface IManagerContentCategoryService {


    List<TreeDto> queryContentCategoryByPid(Long id);
}
