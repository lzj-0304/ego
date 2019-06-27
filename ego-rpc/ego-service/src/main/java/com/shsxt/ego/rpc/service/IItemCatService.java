package com.shsxt.ego.rpc.service;

import com.shsxt.ego.rpc.dto.TreeDto;
import com.shsxt.ego.rpc.pojo.TbItemCat;

import java.util.List;

public interface IItemCatService {
    public List<TreeDto>  queryItemCatByParentId(Long id);
    public List<TbItemCat> queryItemCatsList();
}
