package com.shsxt.ego.manager.controller;

import com.shsxt.ego.manager.service.IManagerContentCategoryService;
import com.shsxt.ego.rpc.dto.TreeDto;
import com.shsxt.ego.rpc.pojo.TbItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ManagerContentCategoryController {

    @Resource
    private IManagerContentCategoryService managerContentCategoryService;

    @RequestMapping("content/category/list")
    @ResponseBody
    public List<TreeDto> queryContentCategoryByParams(@RequestParam(defaultValue="0",required=false) Long id){
        return managerContentCategoryService.queryContentCategoryByPid(id);
    }
}
