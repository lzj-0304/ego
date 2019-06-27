package com.shsxt.ego.manager.controller;

import com.shsxt.ego.manager.service.IManagerContentCategoryService;
import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.rpc.dto.TreeDto;
import com.shsxt.ego.rpc.pojo.TbContentCategory;
import org.springframework.http.MediaType;
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

    /****
     * 处理添加内容分类节点的请求
     */
    @RequestMapping(value="/content/category/create")
    @ResponseBody
    public EgoResult contentCategroyCreate(TbContentCategory contentCategory){

        return managerContentCategoryService.saveContentCategoryService(contentCategory);
    }

    /****
     * 处理添加内容分类节点的请求
     */
    @RequestMapping(value="/content/category/delete",
            produces= MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult contentCategroyDelete(Long id){

        return managerContentCategoryService.deleteContentCategoryService(id);
    }


}
