package com.shsxt.ego.sso.controller;

import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.rpc.pojo.TbUser;
import com.shsxt.ego.sso.service.ISsoUserService;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SsoUserController {

    @Resource
    private ISsoUserService ssoUserService;


    /***
     * 处理用户名  手机号  邮箱唯一性验证请求
     */
    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public MappingJacksonValue userCheck(@PathVariable String param,
                                         @PathVariable Integer type,
                                         @RequestParam(required=false)String callback){
        EgoResult result = ssoUserService.loadUserByCondService(param, type);
        MappingJacksonValue value=new MappingJacksonValue(result);
        //处理json响应数据格式
        if(!StringUtils.isEmpty(callback)){
            value.setJsonpFunction(callback);
        }
        return value;
    }

    /****
     * 处理用户注册请求
     */
    @RequestMapping(value="/user/register",method= RequestMethod.POST)
    @ResponseBody
    public EgoResult userRegister(TbUser tbUser){
        return ssoUserService.saveUserService(tbUser);
    }


    /**
     * 单点登录接口
     * @return
     */
    @RequestMapping(value="/user/login",method=RequestMethod.POST)
    @ResponseBody
    public  EgoResult login(String username, String password,HttpServletRequest request, HttpServletResponse response){
        return ssoUserService.login(username, password,request,response);
    }


    /****
     * 处理获得用户登录状态的请求
     * @return
     */
    @RequestMapping("user/token/{token}")
    @ResponseBody
    public MappingJacksonValue userToken(@PathVariable String token,
                                         @RequestParam(required=false)String callback){

        EgoResult result = ssoUserService.loadUserByToken(token);

        MappingJacksonValue value=new MappingJacksonValue(result);
        //处理json响应数据格式
        if(!StringUtils.isEmpty(callback)){
            value.setJsonpFunction(callback);
        }
        return value;

    }

    /****
     * 处理获得用户登录状态的请求
     * @return
     */
    @RequestMapping("user/logout/{token}")
    @ResponseBody
    public MappingJacksonValue userLogout(@PathVariable String token,
                                          @RequestParam(required=false)String callback){
        EgoResult result = ssoUserService.deleteUserByToken(token);
        MappingJacksonValue value=new MappingJacksonValue(result);
        //处理json响应数据格式
        if(!StringUtils.isEmpty(callback)){
            value.setJsonpFunction(callback);
        }
        return value;
    }









}
