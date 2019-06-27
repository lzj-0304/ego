package com.shsxt.ego.rpc.service;

import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.rpc.dto.TreeDto;
import com.shsxt.ego.rpc.pojo.TbContentCategory;

import java.util.List;

public interface IContentCategoryService {

    List<TreeDto> queryContentCategoryByPid(Long id);

    EgoResult saveTbContentCateGory(TbContentCategory contentCategory);

    EgoResult deleteContentCategoryService(Long id);
}
