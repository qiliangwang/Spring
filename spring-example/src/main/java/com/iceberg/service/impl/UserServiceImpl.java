package com.iceberg.service.impl;


import com.iceberg.dao.UserDao;
import com.iceberg.service.UserService;
import com.iceberg.transaction.TransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

//user 服务层
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private TransactionUtil transactionUtil;
//
    //     spring 事务封装呢？ aop技术
    public void add1() {
        TransactionStatus transactionStatus = null;
        try {
            // 开启事务
            transactionStatus = transactionUtil.begin();
            userDao.add("test001", 20);
            System.out.println("开始报错啦!@!!");
            // int i = 1 / 0;
            System.out.println("################");
            userDao.add("test002", 21);
            // 提交事务
            if (transactionStatus != null)
                transactionUtil.commit(transactionStatus);
        } catch (Exception e) {
            e.getMessage();
            // 回滚事务
            if (transactionStatus != null)
                transactionUtil.rollback(transactionStatus);
        }
    }

    public void add() {
        userDao.add("test001", 20);
        userDao.add("test002", 21);
    }

    public void add3() {
        // 注意事项： 在使用spring事务的时候，service 不要try 最将异常抛出给外层aop 异常通知接受回滚
        try {
            userDao.add("test001", 20);
            int i = 1 / 0;
            System.out.println("################");
            userDao.add("test002", 21);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

}
