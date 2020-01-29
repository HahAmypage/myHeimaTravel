package com.davina.travel.service;

import com.davina.travel.dao.UserDao;
import com.davina.travel.exception.CustomerErrorMsgException;
import com.davina.travel.model.User;
import com.davina.travel.util.Md5Util;

public class UserService {

    private UserDao userDao = new UserDao();

    /**
     * 注册
     * @param user
     * @return
     */
    public boolean register(User user){
        // 1.数据校验合法性
        if (user.getUsername() != null && "".equals(user.getUsername().trim())){
            throw new CustomerErrorMsgException("用户名不能为空");
        }
        // 2.判断用户名是否被注册的
        if (userDao.findByUserName(user.getUsername()) != null){
            throw new CustomerErrorMsgException("用户名已被注册");
        }
        // 3.用户的密码加密
        user.setPassword(Md5Util.getMD5(user.getPassword()));
        // 4.添加用户到数据库
        return userDao.insert(user) > 0 ;
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public User login(String username,String password){
        // 1.判断用户名是否正确，根据用户名获取用户对象就可以判断
        User user = userDao.findByUserName(username);
        if (user == null){
            throw new  CustomerErrorMsgException("用户名不存在");
        }

        // 2.判断密码是否正确
        String md5 = Md5Util.getMD5(password);
        if (!user.getPassword().equals(md5)){
            throw new CustomerErrorMsgException("密码不正确");
        }
        return user;
    }
}
