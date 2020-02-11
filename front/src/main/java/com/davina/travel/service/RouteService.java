package com.davina.travel.service;

import com.davina.travel.dao.RouteDao;
import com.davina.travel.model.*;
import org.apache.commons.beanutils.BeanUtils;

import java.util.List;
import java.util.Map;

public class RouteService {

    private RouteDao routeDao = new RouteDao();

    /**
     * 根据分类id和搜索关键字查线路列表
     * @param cid
     * @param curPageStr
     * @param rname
     * @return
     */
    public PageBean getPageBeanByCid(int cid , String curPageStr, String rname){
        // 创建PageBean对象
        PageBean pageBean = new PageBean();
        // 封装数据
        // 当前页
        // 默认第一页
        int curPage = 1;
        if (curPageStr != null && !"".equals(curPageStr.trim())){
            curPage = Integer.parseInt(curPageStr);
        }
        pageBean.setCurPage(curPage);
        // 每页大小
        int pageSize = 3;
        pageBean.setPageSize(pageSize);
        // 每页数据列表
        pageBean.setDataList(routeDao.findPageByCid(cid,(curPage - 1)* pageSize ,pageSize,rname));
        // 总条数
        pageBean.setCount(routeDao.count(cid,rname));

        return pageBean;
    }

    /**
     * 查询线路详情页信息
     * @param rid
     * @return
     * @throws Exception
     */
    public Route findRoute(int rid) throws Exception{

        Map<String, Object> routeDetails = routeDao.findByRid(rid);

        List<RouteImg> routeImgListByRid = routeDao.findRouteImgListByRid(rid);

        //封装Route数据
        Route route = new Route();
        Category category = new Category();
        Seller seller = new Seller();

        BeanUtils.populate(route,routeDetails);
        BeanUtils.populate(category,routeDetails);
        BeanUtils.populate(seller,routeDetails);

        route.setCategory(category);
        route.setSeller(seller);
        route.setRouteImgList(routeImgListByRid);

        return route;
    }
}
