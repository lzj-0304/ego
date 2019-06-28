package com.shsxt.ego.rpc.service;

import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.rpc.pojo.TbUser;

public interface IUserService {

    EgoResult loadUserByCondService(String param, Integer type);

    EgoResult saveUserTbService(TbUser tbUser);

    TbUser selectUserByUserName(String username);
}
