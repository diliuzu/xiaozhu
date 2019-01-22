package com.xzlx.web.api.dao;


import com.xzlx.web.api.web.dto.FoodDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbFoodDAO {

    List<FoodDTO> selectFoods(@Param("id") Integer id,@Param("start")Integer start,@Param("length") Integer length);

    Integer selectFoodsCount(Integer id);
}
