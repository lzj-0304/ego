package com.shsxt.ego.sso.service.impl;

import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.rpc.pojo.TbUser;
import com.shsxt.ego.rpc.service.IUserService;
import com.shsxt.ego.sso.service.ISsoUserService;
import com.shsxt.ego.utils.CookieUtils;
import com.shsxt.ego.utils.JsonUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
public class SsoUserServiceImpl implements ISsoUserService {
    @Resource
    private IUserService userServiceProxy;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource(name="redisTemplate")
    private ValueOperations<String,Object> valueOperations;
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

    @Override
    public EgoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        EgoResult result=new EgoResult();
        TbUser tbUser = userServiceProxy.selectUserByUserName(username);
        if(tbUser!=null){
            //对前端提交的密码进行加密
            password=DigestUtils.md5DigestAsHex(password.getBytes());
            if(password.equals(tbUser.getPassword())){
                //将当前登录用户对象，转化为json字符串，保存到redis数据
                String jsonStr = JsonUtils.objectToJson(tbUser);
                String token= UUID.randomUUID().toString();
                /**
                 * token 存入缓存
                 */
                valueOperations.set(token,jsonStr);

                // cookie 存入客户端缓存
                CookieUtils.setCookie(request, response, "sso_token", token);
                result.setStatus(200);
                result.setMsg("ok");
                result.setData(token);
                return result;
            }
        }
        result.setStatus(400);
        result.setMsg("error");
        result.setData(null);
        return result;
    }

    @Override
    public EgoResult loadUserByToken(String token) {
        EgoResult result=new EgoResult();

        String jsonStr = (String) valueOperations.get(token);
        if(!StringUtils.isEmpty(jsonStr)){
            result.setStatus(200);
            result.setMsg("ok");

            //result.setData(jsonStr);
            //将jsonStr转化为TbUser对象
            TbUser tbuser = JsonUtils.jsonToPojo(jsonStr, TbUser.class);
            result.setData(tbuser);
            return result;
        }
        result.setStatus(400);
        result.setMsg("error");
        result.setData(null);
        return result;
    }

    @Override
    public EgoResult deleteUserByToken(String token) {
        //创建EgoResult对象
        EgoResult result=new EgoResult();
        //删除redis数据
        redisTemplate.delete(token);
        return result;
    }
}
