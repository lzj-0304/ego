package com.shsxt.ego.rpc.service.impl;

import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.rpc.manager.db.dao.TbUserMapper;
import com.shsxt.ego.rpc.pojo.TbUser;
import com.shsxt.ego.rpc.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private TbUserMapper userMapper;

    @Override
    public EgoResult loadUserByCondService(String param, Integer type) {
        TbUser user= userMapper.loadUserByCond(param,type);
        //创建EgoResult对象
        EgoResult result = new EgoResult();
        result.setStatus(200);
        result.setMsg("ok");
        if(user!=null)
            result.setData(false);
        else
            //用户名可用
            result.setData(true);
        return result;
    }

    @Override
    public EgoResult saveUserTbService(TbUser tbUser) {
        EgoResult result = new EgoResult();
        try {
            Date date=new Date();
            tbUser.setCreated(date);
            tbUser.setUpdated(date);
            userMapper.insertSelective(tbUser);
            result.setMsg("注册成功");
        } catch (Exception e) {
            result.setStatus(400);
            result.setMsg("注册失败. 请校验数据后请再提交数据");
            e.printStackTrace();
        }
        return result;
    }
}
