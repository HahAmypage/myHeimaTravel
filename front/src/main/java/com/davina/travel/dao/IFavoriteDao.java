package com.davina.travel.dao;

import com.davina.travel.model.Favorite;

import java.util.List;
import java.util.Map;

public interface IFavoriteDao {

    /**
     * 查询是否有收藏
     * @param rid
     * @param uid
     * @return
     */
    Favorite findByRidAndUid(int rid, int uid);

    /**
     * 添加收藏
     * @param rid
     * @param date
     * @param uid
     * @return
     */
    int insert(int rid, String date, int uid);

    /**
     * 取消收藏
     * @param rid
     * @param uid
     * @return
     */
    int delete(int rid, int uid);

    /**
     * 更新收藏数量
     * @param rid
     * @return
     */
    int updateCount(int rid, int count);

    /**
     * 查看我的收藏页面，分页结果
     * @param uid
     * @param start
     * @param length
     * @return
     */
    List<Map<String,Object>> findAllByPage(int uid, int start, int length);

    /**
     * 当前用户收藏总数
     * @param uid
     * @return
     */
    int countMyFavorite(int uid);
}
