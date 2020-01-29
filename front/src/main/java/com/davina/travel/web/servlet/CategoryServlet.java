package com.davina.travel.web.servlet;

import com.davina.travel.service.CategoryService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoryServlet",urlPatterns = "/CategoryServlet")
public class CategoryServlet extends BaseServlet {

    private CategoryService categoryService = new CategoryService();

    private void findAllCategory(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String allCategory = categoryService.getAllByRedis();
        response.getWriter().write(allCategory);
    }
}
