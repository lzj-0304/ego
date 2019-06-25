package com.shsxt.ego.manager.controller;

import com.shsxt.ego.manager.service.IFileOperationService;
import com.shsxt.ego.model.PictureResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class FileOperationController {
    @Resource
    private IFileOperationService fileOperationService;

    @RequestMapping(value = "file/upload")
    @ResponseBody
    public PictureResult upload(HttpServletRequest request){
        MultipartHttpServletRequest mhsf = (MultipartHttpServletRequest) request;
        return fileOperationService.uploadFile(mhsf.getFile("uploadFile"));
    }
}
