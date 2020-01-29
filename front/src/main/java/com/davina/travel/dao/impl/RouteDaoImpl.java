package com.davina.travel.dao.impl;

import com.davina.travel.dao.IRouteDao;
import com.davina.travel.model.Route;
import com.davina.travel.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RouteDaoImpl implements IRouteDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());
    /**
     * 收藏排行榜分页数据
     *
     * @param start
     * @param length
     * @return
     */
    @Override
    public List<Route> findFavoriteRankByPage(int start, int length, Map<String,String> conditionMap) {

        String sql = "SELECT * FROM tab_route WHERE rflag = '1' ";

        //定义List集合收集动态个数占位符值
        ArrayList<Object> paramList =  new ArrayList<>();

        //sql语句性能调优的要求：where条件越少查询效率越高，原则多条件查询时条件有效才进行筛选，无效不筛选
        //条件一：rname
        if (conditionMap.get("rname") != null && !"".equals(conditionMap.get("rname").trim())){
            sql += "AND rname LIKE ? ";
            paramList.add("%"+conditionMap.get("rname").trim()+"%");
        }

        //条件二：startPrice
        if (conditionMap.get("startPrice") != null && !"".equals(conditionMap.get("startPrice").trim())){
            sql += "AND price >= ? ";
            paramList.add(conditionMap.get("startPrice").trim());
        }

        //条件三：endPrice
        if (conditionMap.get("endPrice") != null && !"".equals(conditionMap.get("endPrice").trim())){
            sql += "AND price <= ? ";
            paramList.add(conditionMap.get("endPrice").trim());
        }

        sql += "ORDER BY count DESC LIMIT ?,? ";
        paramList.add(start);
        paramList.add(length);

        Object[] parmArray = paramList.toArray();
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Route.class),parmArray);
    }

    /**
     * 收藏排行榜总数
     * @return
     */
    @Override
    public int countFavoriteRank(Map<String,String> conditionMap) {

        String sql = "SELECT COUNT(*) FROM tab_route WHERE rflag = '1' ";

        ArrayList<Object> paramList = new ArrayList<>();

        if (conditionMap.get("rname") != null && !"".equals(conditionMap.get("rname").trim())){
            sql+="AND rname LIKE ? ";
            paramList.add("%"+conditionMap.get("rname").trim()+"%");
        }

        if (conditionMap.get("startPrice") != null && !"".equals(conditionMap.get("startPrice").trim())){
            sql+="AND price >= ? ";
            paramList.add(conditionMap.get("startPrice"));
        }

        if (conditionMap.get("endPrice") != null && !"".equals(conditionMap.get("endPrice").trim())){
            sql+="AND price <= ? ";
            paramList.add(conditionMap.get("endPrice"));
        }

        Object[] parmarray = paramList.toArray();

        return jdbcTemplate.queryForObject(sql,Integer.class,parmarray);
    }
}
