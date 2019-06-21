package com.shsxt.ego.manager.controller;

import com.shsxt.ego.rpc.pojo.TbUser;
import com.shsxt.ego.rpc.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource
    private IUserService userService;


    @RequestMapping(value = "/user/{uid}",method = RequestMethod.GET)
    public @ResponseBody TbUser queryUserByUserId(@PathVariable(value ="uid")Long userId){
        return userService.queryUserByUserId(userId);
    }
}
