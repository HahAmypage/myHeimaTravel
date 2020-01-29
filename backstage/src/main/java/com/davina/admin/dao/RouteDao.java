package com.davina.admin.dao;

import com.davina.admin.model.Route;
import com.davina.admin.util.JdbcUtils;
import com.sun.tools.doclets.formats.html.PackageUseWriter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    /**
     * 旅游线路分页数据
     * @param start
     * @param length
     * @return
     */
    public List<Route> findAllByPage(int start,int length,String rname){
        return jdbcTemplate.query("SELECT * FROM tab_route WHERE rname LIKE ? ORDER BY rdate DESC LIMIT ?,?",new BeanPropertyRowMapper<>(Route.class),"%"+rname+"%",start,length);
    }

    /**
     * 旅游线路总条数
     * @return
     */
    public int count(String rname){
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tab_route WHERE rname LIKE ?",Integer.class,"%"+rname+"%");
    }

    /**
     * 根据id查线路
     * @param rid
     * @return
     */
    public Route findByRid(int rid){
        return jdbcTemplate.queryForObject("SELECT * FROM tab_route WHERE rid = ? ",new BeanPropertyRowMapper<>(Route.class),rid);
    }

    /**
     * 线路信息更新
     * @param route
     * @return
     */
    public int update(Route route){
        return jdbcTemplate.update("UPDATE tab_route SET rname = ? , price = ? ,routeIntroduce = ? , rflag = ? ,cid = ? , rimage = ? WHERE rid = ? ",
                route.getRname(),
                route.getPrice(),
                route.getRouteIntroduce(),
                route.getRflag(),
                route.getCid(),
                route.getRimage(),
                route.getRid());
    }

    /**
     * 删除单个线路
     * @param rid
     * @return
     */
    public int delete(int rid){
        return jdbcTemplate.update("DELETE FROM tab_route WHERE rid = ? ",rid);
    }

    /**
     * 批量删除
     * @param params
     * @return
     */
    public int[] batchDelRoute(List<Object[]> params){
        return jdbcTemplate.batchUpdate("DELETE FROM tab_route WHERE rid = ? ",params);
    }

    public int insert(Route route){
        return jdbcTemplate.update("INSERT INTO tab_route VALUES (null,?,?,?,?,?,?,?,?,?,?,?)",
                route.getRname(),
                route.getPrice(),
                route.getRouteIntroduce(),
                route.getRflag(),
                route.getRdate(),
                route.getIsThemeTour(),
                route.getCount(),
                route.getCid(),
                route.getRimage(),
                route.getSid(),
                null);
    }
}
