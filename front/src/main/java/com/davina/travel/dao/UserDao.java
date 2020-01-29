package com.davina.travel.dao;

import com.davina.travel.model.User;
import com.davina.travel.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    /**
     * 插入新用户信息
     * @param user
     * @return
     */
    public int insert(User user){
        return jdbcTemplate.update("INSERT INTO tab_user VALUES (null,?,?,?,?,?,?,?)",
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail());
    }

    /**
     * 查询用户名是否存在
     * @param username
     * @return
     */
    public User findByUserName(String username){
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM tab_user WHERE username = ? ",new BeanPropertyRowMapper<>(User.class),username);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
//    public User login(String username,String password){
//        try {
//            return jdbcTemplate.queryForObject("SELECT * FROM tab_user WHERE username = ? and password = ? ",new BeanPropertyRowMapper<>(User.class),username,password);
//        }catch (Exception e){
//            return null;
//        }
//    }
}
