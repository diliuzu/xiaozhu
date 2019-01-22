package com.xzlx.web.api.dao;

import com.xzlx.domain.TbScenicSpot;
import com.xzlx.domain.TbTaglib;
import com.xzlx.web.api.web.dto.SearchStrategyDTO;
import com.xzlx.web.api.web.dto.TbHotelDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/22 9:21
 * @Version 1.0
 */
@Repository
public interface SearchDao {

    List<TbHotelDTO> searchHotel(String content);

    List<SearchStrategyDTO> searchStrategy(String content);

    List<TbScenicSpot> searchScenicSpot(String content);

    List<TbTaglib> searchTbTagsByStrategy(int targetId);
}
