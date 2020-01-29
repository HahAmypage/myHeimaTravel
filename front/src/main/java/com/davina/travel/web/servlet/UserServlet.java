package com.davina.travel.web.servlet;

import com.davina.travel.exception.CustomerErrorMsgException;
import com.davina.travel.model.User;
import com.davina.travel.service.IUserServcie;
import com.davina.travel.service.UserService;
import com.davina.travel.util.FactoryUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "UserServlet",urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserService();

    private IUserServcie userService2 = (IUserServcie) FactoryUtil.getInstance("IUserServcie");

    private ObjectMapper objectMapper = new ObjectMapper();

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        doGet(request, response);
//    }

//    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
//
//        String action = request.getParameter("action");
//        if ("register".equals(action)){
//            register(request,response);
//        }else if ("login".equals(action)){
//            login(request,response);
//        }else if ("getLoginUserData".equals(action)){
//            getLoginUserData(request,response);
//        }else if ("logout".equals(action)){
//            logout(request,response);
//        }
//
//    }

    private void addUser(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        userService2.addUser();
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        request.getSession().invalidate();
        response.sendRedirect("login.html");
    }

    private void getLoginUserData(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        // 1.从session里面获取登录数据
        User loginUser = (User) request.getSession().getAttribute("loginUser");

        // 2.登录的用户数据含有多个信息，所以转换为json
        String loginUserJsonString = objectMapper.writeValueAsString(loginUser);

        // 3.输出json字符串给前端
        response.getWriter().write(loginUserJsonString);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{

        String result = "";

        try {

            String dataJsonString = request.getParameter("data");
            Map<String,String> usermap = objectMapper.readValue(dataJsonString, Map.class);
            // 1.获取登录的用户名与密码
            String username = usermap.get("username");
            String password = usermap.get("password");

            // 2.校验验证码
            String checkCode = usermap.get("check");
            String serverCheckCode = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
            if (!checkCode.equalsIgnoreCase(serverCheckCode)){
                result = "验证码错误";
            }else {
                // 3.调用业务进行登录操作
                User user = userService.login(username, password);
                // 4.返回结果用户对象不为空说明登录成功
                if (user != null){
                    request.getSession().setAttribute("loginUser",user);
                    result = "true";
                }
            }

        }catch (CustomerErrorMsgException e){
            result = e.getMessage();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        response.getWriter().write(result);
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String result = "";
        try {
            // 1.获取注册的用户数据并封装到User对象中
            String userJsonString = request.getParameter("data");
            Map<String,String> userMap = objectMapper.readValue(userJsonString, Map.class);
            User user = new User();
            BeanUtils.populate(user,userMap);
            // 2.校验验证码
            String checkCode = userMap.get("check");
            String serverCheckCode = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
            if (!checkCode.equalsIgnoreCase(serverCheckCode)){
                result = "验证码错误";
            }else {
                // 3.调用业务逻辑注册业务方法
                boolean register = userService.register(user);
                if (register){
                    // 4.注册成功，返回true
                    result = "true";
                }
            }

        }catch (CustomerErrorMsgException e){
            result = e.getMessage();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        response.getWriter().write(result);
    }
}
