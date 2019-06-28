package com.shsxt.ego.sso;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/applicationContext-redis.xml"
})
public class TestRedis {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public  void test01(){
        System.out.println("redis。。。");
        redisTemplate.opsForValue().set("sso","sso");
    }
}
