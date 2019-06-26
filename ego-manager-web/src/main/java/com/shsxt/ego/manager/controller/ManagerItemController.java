package com.shsxt.ego.manager.controller;

import com.shsxt.ego.manager.service.IManagerItemService;
import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.model.PageResult;
import com.shsxt.ego.rpc.pojo.TbItem;
import com.shsxt.ego.rpc.query.ItemQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class ManagerItemController {

    @Resource
    private IManagerItemService managerItemService;


    /***
     * 处理商品信息分页查询的请求
     * **/
    @RequestMapping(value="item/list")
    @ResponseBody
    public PageResult<TbItem> itemList(ItemQuery itemQuery){
        return managerItemService.queryItemsByParams(itemQuery);
    }


    /**
     * 处理商品上架的请求
     * @param ids
     * @return
     */
    @RequestMapping(value="item/reshelf")
    @ResponseBody
    public EgoResult reshelfItem(Long[] ids){
        return managerItemService.shelfItem(ids);
    }

    /***
     * 处理商品下架请求
     * **/
    @RequestMapping(value="item/instock")
    @ResponseBody
    public EgoResult instockItem(Long[] ids){
        return managerItemService.instockItem(ids);
    }

    /***
     * 处理商品信息的删除请求
     * **/
    @RequestMapping(value="item/delete")
    @ResponseBody
    public EgoResult itemDelete(Long[] ids){
        return managerItemService.itemDelete(ids);
    }


    /**
     * 保存商品
     * @param tbItem
     * @return
     */
    @RequestMapping("item/save")
    @ResponseBody
    public EgoResult saveItem(TbItem tbItem){
        return managerItemService.saveItem(tbItem);
    }

    @RequestMapping("item/update")
    @ResponseBody
    public EgoResult updateItem(TbItem tbItem){
        return managerItemService.updateItem(tbItem);
    }




}
