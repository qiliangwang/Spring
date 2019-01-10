package com.iceberg;

import com.iceberg.proxy.UserServiceProxy;
import com.iceberg.service.UserService;
import com.iceberg.service.impl.UserServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApplication {
    public static void main(String[] args) {
//        UserServiceImpl userService = new UserServiceImpl();
//        UserServiceProxy userServiceProxy = new UserServiceProxy(userService);
//        userServiceProxy.add();

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        userService.add();
        System.out.println("add 2 row data to mysql");
    }
}
