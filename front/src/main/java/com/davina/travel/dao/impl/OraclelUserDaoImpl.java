package com.davina.travel.dao.impl;

import com.davina.travel.dao.IUserDao;

public class OraclelUserDaoImpl implements IUserDao {
    @Override
    public int addUser() {
        System.out.println("执行oracle添加用户成功。。。");
        return 1;
    }
}
