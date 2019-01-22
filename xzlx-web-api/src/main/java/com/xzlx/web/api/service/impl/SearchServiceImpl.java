package com.xzlx.web.api.service.impl;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.domain.TbScenicSpot;
import com.xzlx.domain.TbTaglib;
import com.xzlx.web.api.dao.SearchDao;
import com.xzlx.web.api.service.SearchService;
import com.xzlx.web.api.web.dto.SearchStrategyDTO;
import com.xzlx.web.api.web.dto.TbHotelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/22 9:20
 * @Version 1.0
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao dao;

    @Override
    public BaseResult search(String kind, String content) {
        BaseResult baseResult = null;
        if (kind.equals("hotel")){
            List<TbHotelDTO> tbHotelDTOS = dao.searchHotel(content);
            baseResult = BaseResult.success("查询酒店成功",tbHotelDTOS);
        }else if (kind.equals("strategy")){
            List<SearchStrategyDTO> searchStrategyDTOS = dao.searchStrategy(content);
            for (SearchStrategyDTO searchStrategyDTO : searchStrategyDTOS) {
                List<TbTaglib> tags = dao.searchTbTagsByStrategy(searchStrategyDTO.getId());
                searchStrategyDTO.setTags(tags);
            }
            baseResult = BaseResult.success("查询攻略成功",searchStrategyDTOS);
        }else if (kind.equals("scenicSpot")){
            List<TbScenicSpot> scenicSpots = dao.searchScenicSpot(content);
            baseResult = BaseResult.success("查询景点成功",scenicSpots);
        }else{
            baseResult = BaseResult.fail("查询失败");
        }
        return baseResult;
    }
}
