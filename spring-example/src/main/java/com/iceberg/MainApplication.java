package com.iceberg;

import com.iceberg.proxy.UserServiceProxy;
import com.iceberg.service.impl.UserServiceImpl;

public class MainApplication {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        UserServiceProxy userServiceProxy = new UserServiceProxy(userService);
        userServiceProxy.add();
    }
}
