package com.davina.admin.service;

import com.davina.admin.dao.CategoryDao;
import com.davina.admin.model.Category;

import java.util.List;

public class CategoryService {

    private CategoryDao categoryDao = new CategoryDao();

    /**
     * 查询所有类别
     * @return
     */
    public List<Category> findAll(){
        return categoryDao.findAll();
    }
}
