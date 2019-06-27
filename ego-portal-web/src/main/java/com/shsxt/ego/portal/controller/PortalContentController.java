package com.shsxt.ego.portal.controller;

import com.shsxt.ego.portal.service.IPortalContentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class PortalContentController {
    @Resource
    private IPortalContentService portalContentService;

    @RequestMapping(value="/content/index/list",produces= MediaType.TEXT_HTML_VALUE+";charset=UTF-8")
    @ResponseBody
    public String contentIndexList(Long categoryId){
        return portalContentService.loadContentListByCidService(categoryId);
    }
}
