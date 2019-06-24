package com.shsxt.ego.rpc.service.impl;

import com.shsxt.ego.rpc.service.IUserService;
import com.shsxt.ego.rpc.manager.db.dao.TbUserMapper;
import com.shsxt.ego.rpc.pojo.TbUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private TbUserMapper userMapper;
    @Override
    public TbUser queryUserByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
