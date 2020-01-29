package com.davina.travel.service;

import com.davina.travel.model.PageBean;
import com.davina.travel.model.Route;

import java.util.Map;

public interface IRouteService {

    /**
     * 收藏排行榜分页数据
     * @param curPageStr
     * @return
     */
    PageBean<Route> getPageBeanByFavoriteRank(String curPageStr, Map<String, String> conditionMap);

}
