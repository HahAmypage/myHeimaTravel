package com.davina.travel.dao.impl;

import com.davina.travel.dao.IFavoriteDao;
import com.davina.travel.model.Favorite;
import com.davina.travel.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class FavoriteDaoImpl implements IFavoriteDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    /**
     * 查询是否有收藏
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM tab_favorite WHERE rid = ? AND uid = ? ",new BeanPropertyRowMapper<>(Favorite.class),rid,uid);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 添加收藏
     * @param rid
     * @param date
     * @param uid
     * @return
     */
    @Override
    public int insert(int rid, String date, int uid) {
        return jdbcTemplate.update("INSERT INTO tab_favorite VALUES (?,?,?) ",rid,date,uid);
    }

    /**
     * 取消收藏
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public int delete(int rid, int uid) {
        return jdbcTemplate.update("DELETE FROM tab_favorite WHERE rid = ? AND uid = ? ",rid,uid);
    }

    /**
     * 更新收藏数量
     * @param rid
     * @return
     */
    @Override
    public int updateCount(int rid,int count) {
        return jdbcTemplate.update("UPDATE tab_route SET count = count + ?  WHERE rid = ?",count,rid);
    }

    /**
     * 查看我的收藏页面
     *
     * @param uid
     * @param start
     * @param length
     * @return
     */
    @Override
    public List<Map<String, Object>> findAllByPage(int uid, int start, int length) {
        try{
            return jdbcTemplate.queryForList("SELECT * FROM tab_favorite f,tab_route r WHERE f.rid = r.rid AND uid = ? LIMIT ?,?",uid,start,length);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 当前用户收藏总数
     *
     * @param uid
     * @return
     */
    @Override
    public int countMyFavorite(int uid) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tab_favorite f WHERE f.uid = ? ",Integer.class,uid);
    }
}
