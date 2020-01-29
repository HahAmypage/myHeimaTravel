package com.davina.travel.service;

import com.davina.travel.model.Favorite;
import com.davina.travel.model.PageBean;

public interface IFavoriteService {

    /**
     * 查询是否有收藏
     *
     * @param rid
     * @param uid
     * @return
     */
    boolean isFavoriteByRidAndUid(int rid, int uid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     * @return
     */
    int addFavorite(int rid, int uid) throws Exception;

    /**
     * 取消收藏
     * @param rid
     * @param uid
     * @return
     * @throws Exception
     */
    int cancelFavorite(int rid, int uid) throws Exception;

    /**
     * 获取当前用户收藏的分页数据
     * @param uid
     * @param curPageStr
     * @return
     */
    PageBean<Favorite> getPageBean(int uid, String curPageStr) throws Exception;
}
