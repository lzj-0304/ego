package com.shsxt.ego.manager.controller;

import com.shsxt.ego.manager.service.IManagerContentService;
import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.model.PageResult;
import com.shsxt.ego.rpc.pojo.TbContent;
import com.shsxt.ego.rpc.query.ContentQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class ManagerContentController {
    @Resource
    private IManagerContentService managerContentService;

    /****
     * 处理内容分页查询请求
     */
    @RequestMapping(value="/content/query/list")
    @ResponseBody
    public PageResult<TbContent> contentQueryList(ContentQuery contentQuery){
        return managerContentService.loadContentListService(contentQuery);
    }

    /****
     * 处理内容添加请求
     */
    @RequestMapping(value="/content/save")
    @ResponseBody
    public EgoResult contentSave(TbContent tbContent){
        return managerContentService.saveContentService(tbContent);
    }

    /****
     * 处理内容删除请求
     */
    @RequestMapping(value="/content/delete")
    @ResponseBody
    public EgoResult contentDelete(String ids){
        return managerContentService.deleteContentService(ids);
    }

    /****
     * 处理内容更新请求
     */
    @RequestMapping(value="/rest/content/edit")
    @ResponseBody
    public EgoResult contentEdit(TbContent tbContent){
        return managerContentService.updateContentService(tbContent);
    }


}
