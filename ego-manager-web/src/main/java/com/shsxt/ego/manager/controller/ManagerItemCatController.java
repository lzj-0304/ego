package com.shsxt.ego.manager.controller;

import com.shsxt.ego.rpc.dto.TreeDto;
import com.shsxt.ego.rpc.service.IItemCatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ManagerItemCatController {
    @Resource
    private IItemCatService itemCatService;


    /**
     * 查询所有类目 ztree
     * @return
     */
    @RequestMapping("item/cat/list")
    @ResponseBody
    public List<TreeDto>  queryItemCatByParentId(@RequestParam(defaultValue="0",required=false) Long id){
        return itemCatService.queryItemCatByParentId(id);
    }
}
