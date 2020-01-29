package com.davina.admin.dao;

import com.davina.admin.model.Category;
import com.davina.admin.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    /**
     * 查询所有类别
     * @return
     */
    public List<Category> findAll(){
        return jdbcTemplate.query("SELECT * FROM tab_category ORDER BY cid ",new BeanPropertyRowMapper<>(Category.class));
    }
}
