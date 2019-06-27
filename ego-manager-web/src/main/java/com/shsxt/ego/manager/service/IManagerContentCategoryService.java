package com.shsxt.ego.manager.service;

import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.rpc.dto.TreeDto;
import com.shsxt.ego.rpc.pojo.TbContentCategory;

import java.util.List;

public interface IManagerContentCategoryService {


    List<TreeDto> queryContentCategoryByPid(Long id);

    EgoResult saveContentCategoryService(TbContentCategory contentCategory);

    EgoResult deleteContentCategoryService(Long id);
}
