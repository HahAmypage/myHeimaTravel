package com.davina.travel.service.impl;

import com.davina.travel.dao.IRouteDao;
import com.davina.travel.model.PageBean;
import com.davina.travel.model.Route;
import com.davina.travel.service.IRouteService;
import com.davina.travel.util.FactoryUtil;

import java.util.List;
import java.util.Map;

public class RouteServiceImpl implements IRouteService {

    IRouteDao iRouteDao = (IRouteDao) FactoryUtil.getInstance("IRouteDao");

    /**
     * 收藏排行榜分页数据
     *
     * @param curPageStr
     * @return
     */
    @Override
    public PageBean<Route> getPageBeanByFavoriteRank(String curPageStr, Map<String,String> conditionMap) {

        PageBean<Route> routePageBean = new PageBean<>();

        //当前页
        int curPage = 1;
        if (curPageStr != null && !"".equals(curPageStr.trim())){
            curPage = Integer.parseInt(curPageStr);
        }

        routePageBean.setCurPage(curPage);

        // 页大小
        int pageSize = 10;
        routePageBean.setPageSize(pageSize);

        // 总条数
        int favoriteRankCount = iRouteDao.countFavoriteRank(conditionMap);
        routePageBean.setCount(favoriteRankCount);

        // 封装每页数据
        List<Route> favoriteRankByPage = iRouteDao.findFavoriteRankByPage((curPage - 1) * pageSize, pageSize,conditionMap);
        if (favoriteRankByPage != null && favoriteRankByPage.size() > 0){
            routePageBean.setDataList(favoriteRankByPage);
        }

        return routePageBean;
    }
}
