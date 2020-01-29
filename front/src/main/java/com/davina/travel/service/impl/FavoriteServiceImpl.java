package com.davina.travel.service.impl;

import com.davina.travel.dao.IFavoriteDao;
import com.davina.travel.model.Favorite;
import com.davina.travel.model.PageBean;
import com.davina.travel.model.Route;
import com.davina.travel.service.IFavoriteService;
import com.davina.travel.service.RouteService;
import com.davina.travel.util.FactoryUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FavoriteServiceImpl implements IFavoriteService {

    private IFavoriteDao iFavoriteDao = (IFavoriteDao) FactoryUtil.getInstance("IFavoriteDao");

    private RouteService routeService = new RouteService();

    /**
     * 查询是否有收藏
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public boolean isFavoriteByRidAndUid(int rid, int uid) {
        Favorite favorite = iFavoriteDao.findByRidAndUid(rid, uid);
        return favorite != null;
    }

    /**
     * 添加收藏
     * @param rid
     * @param uid
     * @return
     * @throws Exception
     */
    @Override
    public int addFavorite(int rid, int uid) throws Exception {

        //1.调用dao层实现添加收藏记录和更新收藏数量
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        iFavoriteDao.insert(rid,date,uid);
        iFavoriteDao.updateCount(rid,1);
        //2.根据rid查找最新线路数据，返回收藏数量
        Route route = routeService.findRoute(rid);

        return route.getCount();
    }

    /**
     * 取消收藏
     *
     * @param rid
     * @param uid
     * @return
     * @throws Exception
     */
    @Override
    public int cancelFavorite(int rid, int uid) throws Exception {
        //调dao删除收藏记录
        iFavoriteDao.delete(rid,uid);
        iFavoriteDao.updateCount(rid,-1);
        //根据rid查最新线路数据，更新收藏数量
        Route route = routeService.findRoute(rid);
        return route.getCount();
    }

    /**
     * 获取当前用户收藏的分页数据
     *
     * @param uid
     * @param curPageStr
     * @return
     */
    @Override
    public PageBean<Favorite> getPageBean(int uid, String curPageStr) throws Exception {

        PageBean pageBean = new PageBean();

        // 封装当前页
        int curPage = 1;
        if (curPageStr !=  null && !"".equals(curPageStr.trim())){
            curPage = Integer.parseInt(curPageStr);
        }
        pageBean.setCurPage(curPage);

        // 封装总条数
        int count = iFavoriteDao.countMyFavorite(uid);
        pageBean.setCount(count);

        // 封装页大小
        int pageSize = 4;
        pageBean.setPageSize(pageSize);

        List<Map<String, Object>> myFavoiteList = iFavoriteDao.findAllByPage(uid, (curPage - 1) * pageSize, pageSize);
        List<Favorite> favoriteList = null;
        if (myFavoiteList != null && myFavoiteList.size() > 0){
            favoriteList = new ArrayList<>();
            for (Map<String,Object> map:myFavoiteList){
                Favorite favorite = new Favorite();
                Route route = new Route();
                BeanUtils.populate(favorite,map);
                BeanUtils.populate(route,map);
                favorite.setRoute(route);
                //最后将favorite追加到List<Favorite>
                favoriteList.add(favorite);
            }
        }

        pageBean.setDataList(favoriteList);
        return pageBean;
    }
}
