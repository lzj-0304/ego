package com.shsxt.ego.manager.controller;

import com.shsxt.ego.manager.service.IManagerItemParamItemService;
import com.shsxt.ego.model.EgoResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 商品规格
 */
@Controller
public class ManagerItemParamItemController {
    @Resource
    private IManagerItemParamItemService itemParamItemService;



    /****
     * 处理根据商品类名id，查询规格模板请求
     * @return
     */
    @RequestMapping(value="param/item/query/{itemId}")
    @ResponseBody
    public EgoResult itemParamQuery(@PathVariable Long itemId){
        return itemParamItemService.queryItemParamItemByItemId(itemId);
    }



}
