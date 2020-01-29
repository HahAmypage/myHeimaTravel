package com.davina.travel.dao;

import com.davina.travel.model.Category;
import com.davina.travel.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    /**
     * 查询类别
     * @return
     */
    public List<Category> findAll(){
        return jdbcTemplate.query("SELECT * FROM tab_category ORDER BY cid ",new BeanPropertyRowMapper<>(Category.class));
    }
}
