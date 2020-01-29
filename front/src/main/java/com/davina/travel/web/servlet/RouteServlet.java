package com.davina.travel.web.servlet;

import com.davina.travel.model.PageBean;
import com.davina.travel.model.Route;
import com.davina.travel.service.IRouteService;
import com.davina.travel.service.RouteService;
import com.davina.travel.util.FactoryUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RouteServlet",urlPatterns = "/RouteServlet")
public class RouteServlet extends BaseServlet {

    private RouteService routeService = new RouteService();

    private ObjectMapper objectMapper = new ObjectMapper();

    IRouteService iRouteService = (IRouteService) FactoryUtil.getInstance("IRouteService");

    /**
     * 根据类别id和搜索关键字查询线路列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findPageByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 获取请求数据cid 和 curPage
        int cid = Integer.parseInt(request.getParameter("cid"));
        String curPage = request.getParameter("curPage");
        String rname = request.getParameter("rname");

        // 调用业务层获取分页PageBean数据
        PageBean pageBeanByCid = routeService.getPageBeanByCid(cid, curPage,rname);

        // 将获取的PageBean转换为json字符串，在转换时会调用PageBean每个成员的get方法获取数据
        String pageBeanJsonString = objectMapper.writeValueAsString(pageBeanByCid);
        response.getWriter().write(pageBeanJsonString);

    }

    /**
     * 根据id查某线路的详情
     * @param request
     * @param response
     * @throws Exception
     */
    public void findRouteByRid(HttpServletRequest request,HttpServletResponse response) throws Exception{

        int rid = Integer.parseInt(request.getParameter("rid"));
        Route route = routeService.findRoute(rid);

        String routeJsonString = objectMapper.writeValueAsString(route);

        response.getWriter().write(routeJsonString);

    }

    public void findFavoriteRankByPage(HttpServletRequest request,HttpServletResponse response) throws Exception{

        String curPage = request.getParameter("curPage");
        Map<String,String> conditionMap = new HashMap<>();
        conditionMap.put("rname",request.getParameter("rname"));
        conditionMap.put("startPrice",request.getParameter("startPrice"));
        conditionMap.put("endPrice",request.getParameter("endPrice"));
        PageBean<Route> pageBeanByFavoriteRank = iRouteService.getPageBeanByFavoriteRank(curPage,conditionMap);
        response.getWriter().write(objectMapper.writeValueAsString(pageBeanByFavoriteRank));
    }
}
