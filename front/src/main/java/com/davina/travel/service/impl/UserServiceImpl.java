package com.davina.travel.service.impl;

import com.davina.travel.dao.IUserDao;
import com.davina.travel.service.IUserServcie;
import com.davina.travel.util.FactoryUtil;

public class UserServiceImpl implements IUserServcie {

    IUserDao userDao = (IUserDao) FactoryUtil.getInstance("IUserDao");

    @Override
    public boolean addUser() {

        return userDao.addUser()>0;
    }
}
