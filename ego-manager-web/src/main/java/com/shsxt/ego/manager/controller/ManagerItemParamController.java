package com.shsxt.ego.manager.controller;

import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.model.PageResult;
import com.shsxt.ego.rpc.pojo.TbItemParam;
import com.shsxt.ego.rpc.query.ItemParamQuery;
import com.shsxt.ego.rpc.service.IItemParamService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class ManagerItemParamController {
    @Resource
    private IItemParamService itemParamService;

    /***
     * 处理商品信息分页查询的请求
     * **/
    @RequestMapping(value="itemParam/list")
    @ResponseBody
    public PageResult<TbItemParam> itemList(ItemParamQuery itemParamQuery){
        return itemParamService.queryItemParamByParams(itemParamQuery);
    }

    /****
     * 处理根据商品类名id，查询规格模板请求
     * @return
     */
    @RequestMapping(value="/itemParam/query/{cid}")
    @ResponseBody
    public EgoResult itemParamQuery(@PathVariable Long cid){
        return itemParamService.loadItemParamByCid(cid);
    }

    /****
     * 处理新增商品规格参数模板的请求
     * @param cid
     * @param paramData
     * @return
     */
    @RequestMapping(value="/itemParam/save/{cid}")
    @ResponseBody
    public EgoResult saveItemParam(@PathVariable Long cid,String paramData){

        return itemParamService.saveItemParamService(cid, paramData);
    }

    @RequestMapping(value="/itemParam/delete")
    @ResponseBody
    public EgoResult deleteItemParam(Long[] ids){

        return itemParamService.deleteItemParam(ids);
    }
}
