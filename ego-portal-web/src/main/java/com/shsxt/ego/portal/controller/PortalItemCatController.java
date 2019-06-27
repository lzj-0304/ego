package com.shsxt.ego.portal.controller;

import com.shsxt.ego.portal.service.IPortalItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PortalItemCatController {
    //注入service对象
    @Autowired
    private IPortalItemCatService portalItemCatService;
    /****
     * 处理加载商品类目的请求
     */
    @RequestMapping(value="/item/cat",produces= MediaType.TEXT_HTML_VALUE+";charset=UTF-8")
    @ResponseBody
    public String itemCat(){
        return portalItemCatService.loadItemCatService();
    }

    @RequestMapping("test")
    public void test(){
        System.out.println("this is test............");
    }
}
