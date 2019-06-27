package com.shsxt.ego.sso.service;

import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.rpc.pojo.TbUser;

public interface ISsoUserService {
    EgoResult loadUserByCondService(String param, Integer type);

    EgoResult saveUserService(TbUser tbUser);
}
