package com.davina.admin.service;

import com.davina.admin.dao.RouteDao;
import com.davina.admin.model.PageBean;
import com.davina.admin.model.Route;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RouteService {

    private RouteDao routeDao = new RouteDao();

    public PageBean<Route> getPageBean(String curPageStr,String rname){

        // 封装PageBean
        PageBean<Route> routePageBean = new PageBean<>();

        // 当前页
        int curPage = 1;
        if (curPageStr != null && !"".equals(curPageStr.trim())){
            curPage = Integer.parseInt(curPageStr);
        }
        routePageBean.setCurPage(curPage);

        if (rname == null || "".equals(rname.trim())){
            rname = "";
        }

        // 每页大小
        int pageSize = 10;
        routePageBean.setPageSize(pageSize);

        // 总条数
        int count = routeDao.count(rname);
        routePageBean.setCount(count);

        // 封装每页数据
        List<Route> routeList = routeDao.findAllByPage((curPage - 1) * pageSize, pageSize,rname);
        routePageBean.setDataList(routeList);

        return routePageBean;
    }

    /**
     * 根据id查询线路
     * @param rid
     * @return
     */
    public Route findByRid(int rid){
        Route route = routeDao.findByRid(rid);
        return route;
    }

    /**
     * 更新线路信息
     * @param route
     * @return
     */
    public boolean update(Route route){
        int update = routeDao.update(route);
        return update > 0;
    }

    /**
     * 删除单个线路
     * @param rid
     * @return
     */
    public boolean delRoute(int rid){
        int delete = routeDao.delete(rid);
        return delete > 0;
    }

    /**
     * 批量删除
     * @param rids
     * @return
     */
    public boolean batchDelRoute(String[] rids) {
        try {
            List<Object[]> params = new ArrayList<>();
            for (String s: rids){
                Object[] objects = {s};
                params.add(objects);
            }
            routeDao.batchDelRoute(params);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 添加线路
     * @param route
     * @return
     */
    public boolean addRoute(Route route){
        // 封装上架时间，是否主题旅游，收藏数量，上传图片地址，所属上架id，
        // 注意HH是24小时制，hh是12小时制，如果用12小时制会影响列表排序
        route.setRdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        route.setIsThemeTour("0");
        route.setCount(0);
        route.setSid(1);
        // 调用添加线路
        return routeDao.insert(route)>0;
    }
}
