package com.davina.travel.web.servlet;

import com.davina.travel.model.Favorite;
import com.davina.travel.model.PageBean;
import com.davina.travel.model.User;
import com.davina.travel.service.IFavoriteService;
import com.davina.travel.util.FactoryUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FavoriteServlet",urlPatterns = "/FavoriteServlet")
public class FavoriteServlet extends BaseServlet {

    private IFavoriteService iFavoriteService = (IFavoriteService) FactoryUtil.getInstance("IFavoriteService");

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 查询是否有收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavoriteByRid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        int rid = Integer.parseInt(request.getParameter("rid"));

        User loginUser = (User) request.getSession().getAttribute("loginUser");

        if (loginUser == null){ //没有登录
            response.getWriter().write("false");
        }else {
            boolean isFavorite = iFavoriteService.isFavoriteByRidAndUid(rid, loginUser.getUid());
            response.getWriter().print(isFavorite);
        }
    }

    /**
     * 添加收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request,HttpServletResponse response) throws Exception{

        int rid = Integer.parseInt(request.getParameter("rid"));
        User user = (User) request.getSession().getAttribute("loginUser");

        if (user == null){
            response.getWriter().write("false");
        }else {
            int addFavorite = iFavoriteService.addFavorite(rid, user.getUid());
            response.getWriter().print(addFavorite);
        }
    }

    /**
     * 取消收藏
     * @param request
     * @param response
     * @throws Exception
     */
    public void cancelFavorite(HttpServletRequest request,HttpServletResponse response) throws Exception{

        int rid = Integer.parseInt(request.getParameter("rid"));
        User user = (User) request.getSession().getAttribute("loginUser");

        if (user == null){
            response.getWriter().write("false");
        }else {
            int cancelFavorite = iFavoriteService.cancelFavorite(rid, user.getUid());
            response.getWriter().print(cancelFavorite);
        }
    }

    public void findFavoriteByPage(HttpServletRequest request,HttpServletResponse response) throws Exception{

        String curPage = request.getParameter("curPage");
        User loginUser = (User) request.getSession().getAttribute("loginUser");

        PageBean<Favorite> pageBean = null;
        if (loginUser != null){
            pageBean = iFavoriteService.getPageBean(loginUser.getUid(), curPage);
        }
        String myFavoritePageJsonString = objectMapper.writeValueAsString(pageBean);
        response.getWriter().print(myFavoritePageJsonString);
    }
}
