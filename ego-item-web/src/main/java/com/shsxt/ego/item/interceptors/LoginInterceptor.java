package com.shsxt.ego.item.interceptors;

import com.shsxt.ego.rpc.pojo.TbUser;
import com.shsxt.ego.utils.CookieUtils;
import com.shsxt.ego.utils.JsonUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOperations;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 获取cookie 校验redis 是否存在用户登录信息
         *   存在 放行
         *   不存在
         *      跳转至登录页(包含登录后回来的页面)
         */
        //获得token
        String token = CookieUtils.getCookieValue(request, "sso_token");
        if (!StringUtils.isEmpty(token)) {
            //查询redis数据
            String jsonStr = (String) valueOperations.get(token);
            //验证用户是否登录
            if (!StringUtils.isEmpty(jsonStr)) {
                TbUser tbuser = JsonUtils.jsonToPojo(jsonStr, TbUser.class);
                request.setAttribute("user", tbuser);
                //说明登录，放行
                return true;
            }
        }
        //在用户登录成功后需要调整的路径
        String url = request.getRequestURL().toString();
        //重定向到登录页面
        response.sendRedirect("http://localhost:8083/login?redirect=" + url);

        return false;

    }
}
