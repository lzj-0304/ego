package com.shsxt.ego;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Starter {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac=new ClassPathXmlApplicationContext(
                "spring/applicationContext-dao.xml",
                "spring/applicationContext-service.xml",
                "spring/applicationContext-tx.xml",
                "spring/applicationContext-dubbo.xml");
       /*IUserService userService= (IUserService) ac.getBean("userServiceImpl");
        System.out.println(userService.queryUserByUserId(Long.parseLong("7")).getUsername());*/
        ac.start();
        //阻塞程序的运行
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ac.stop();
    }
}
