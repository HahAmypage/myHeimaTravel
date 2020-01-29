package com.davina.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet(name = "BaseServlet",urlPatterns = "/BaseServlet")
public class BaseServlet extends HttpServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException,IOException{
        try {
            //将父接口转换成子接口
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;

            //目标：所有请求先走BaseServlet,获取action,使用反射自动让同名方法自动执行
            //需要字符串的方法名和字节码Class对象
            String action = request.getParameter("action");
            Class clazz = this.getClass();
            Method declaredMethod = clazz.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            declaredMethod.setAccessible(true);
            //调用方法(因为action值和实际处理的方法名称相同)
            declaredMethod.invoke(this,httpServletRequest,httpServletResponse);

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
