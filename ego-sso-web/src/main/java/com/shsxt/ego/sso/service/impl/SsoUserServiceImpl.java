package com.shsxt.ego.sso.service.impl;

import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.rpc.pojo.TbUser;
import com.shsxt.ego.rpc.service.IUserService;
import com.shsxt.ego.sso.service.ISsoUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Service
public class SsoUserServiceImpl implements ISsoUserService {
    @Resource
    private IUserService userServiceProxy;
    @Override
    public EgoResult loadUserByCondService(String param, Integer type) {
        return userServiceProxy.loadUserByCondService(param,type);
    }

    @Override
    public EgoResult saveUserService(TbUser tbUser) {
        String pwd=tbUser.getPassword();
        String md5 = DigestUtils.md5DigestAsHex(pwd.getBytes());
        tbUser.setPassword(md5);
        return userServiceProxy.saveUserTbService(tbUser);
    }
}
