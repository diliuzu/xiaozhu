package com.xzlx.web.api.dao;

import com.xzlx.domain.TbHotelType;
import com.xzlx.web.api.web.dto.TbHotelDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/18 14:41
 * @Version 1.0
 */
@Repository
public interface TbHotalDao {
    List<TbHotelDTO> selectByAreaId(Map map);
    int selectByAreaIdCount(int area_id);
    List<TbHotelType> selectType();
    List<TbHotelDTO> selectByTypeId(Map map);
    int selectByTypeIdCount(int typeId);
    List<TbHotelDTO> selectById(int id);
    double selectMinPrice(int id);
}
