package com.davina.travel.dao;

import com.davina.travel.model.Route;
import com.davina.travel.model.RouteImg;
import com.davina.travel.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class RouteDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    /**
     * 总条数
     * @param cid
     * @return
     */
    public int count(int cid,String rname){
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tab_route WHERE cid = ? AND rname LIKE  ? ",Integer.class , cid,"%"+rname+"%");
    }

    /**
     * 分页列表
     * @param cid
     * @param start
     * @param length
     * @param rname
     * @return
     */
    public List<Route> findPageByCid(int cid , int start ,int length,String rname ){
        return jdbcTemplate.query("SELECT * FROM tab_route WHERE cid = ? AND rname LIKE ? LIMIT ? , ? ",new BeanPropertyRowMapper<>(Route.class),cid , "%"+rname+"%" ,start ,length);
    }

    /**
     * 根据id查指定线路
     * @param rid
     * @return
     */
    public Map<String,Object> findByRid(int rid){
        try {
            return jdbcTemplate.queryForMap("SELECT * FROM tab_route r,tab_category c,tab_seller s WHERE r.cid = c.cid AND r.sid = s.sid AND rid = ? ",rid);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 查指定线路的轮播图
     * @param rid
     * @return
     */
    public List<RouteImg> findRouteImgListByRid(int rid){
        return jdbcTemplate.query("SELECT * FROM tab_route_img WHERE rid = ?",new BeanPropertyRowMapper<>(RouteImg.class),rid);
    }
}
