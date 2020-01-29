package com.davina.travel.dao.impl;

import com.davina.travel.dao.IUserDao;

public class MySqlUserDaoImpl implements IUserDao {
    @Override
    public int addUser() {
        System.out.println("执行mysql添加用户成功。。。");
        return 1;
    }
}
