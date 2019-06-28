package com.shsxt.ego.sso.service;

import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.rpc.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ISsoUserService {
    EgoResult loadUserByCondService(String param, Integer type);

    EgoResult saveUserService(TbUser tbUser);

    EgoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response);

    EgoResult loadUserByToken(String token);

    EgoResult deleteUserByToken(String token);
}
