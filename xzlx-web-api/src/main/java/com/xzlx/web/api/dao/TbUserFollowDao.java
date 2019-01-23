package com.xzlx.web.api.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TbUserFollowDao {
    int personalshowfollow(int id);
    int followershow(int id);
    Integer select(@Param("edId") Integer userId, @Param("id") Integer id);

    void addFollow(@Param("edId") Integer userId, @Param("id") Integer id);

    void removeFollow(@Param("edId") Integer userId, @Param("id") Integer id);
}
