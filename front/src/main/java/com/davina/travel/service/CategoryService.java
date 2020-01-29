package com.davina.travel.service;

import com.davina.travel.dao.CategoryDao;
import com.davina.travel.model.Category;
import com.davina.travel.util.JedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

public class CategoryService {

    private CategoryDao categoryDao = new CategoryDao();

    private ObjectMapper objectMapper = new ObjectMapper();

    public String getAllByRedis() throws Exception{
        //先从redis取数据
        Jedis jedis = JedisUtil.getJedis();
        String categories = jedis.get("categories");

        if (categories == null){
            List<Category> allCategory = categoryDao.findAll();
            if (allCategory != null && allCategory.size() > 0){
                categories = objectMapper.writeValueAsString(allCategory);
                jedis.set("categories",categories);
            }
        }
        jedis.close();
        return categories;
    }
}
