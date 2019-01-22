package com.xzlx.web.api.dao;

import com.xzlx.web.api.web.dto.Shop;
import com.xzlx.web.api.web.dto.ShopDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbShopDAO {
    //获取parentId=0的shop
    List<ShopDTO> selectParentById(@Param("id") Integer areaId, @Param("start") Integer start, @Param("length") Integer length);

    //获取总的记录条数
    Integer selectParentByIdCount(@Param("id") Integer areaId);

    //获取子类记录
    List<Shop> selectByParentId(@Param("id") Integer id);

    Shop selectById(Integer id);
}
