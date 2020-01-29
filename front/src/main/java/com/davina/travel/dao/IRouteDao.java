package com.davina.travel.dao;

import com.davina.travel.model.Route;

import java.util.List;
import java.util.Map;

public interface IRouteDao {

    /**
     * 收藏排行榜分页数据
     * @param start
     * @param length
     * @return
     */
    List<Route> findFavoriteRankByPage(int start, int length, Map<String, String> conditionMap);

    /**
     * 收藏排行榜总数
     * @return
     */
    int countFavoriteRank(Map<String, String> conditionMap);
}
