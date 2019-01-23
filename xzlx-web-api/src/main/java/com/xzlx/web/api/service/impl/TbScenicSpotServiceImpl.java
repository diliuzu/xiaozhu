package com.xzlx.web.api.service.impl;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.commons.util.persistance.BasePageDTO;
import com.xzlx.domain.TbScenicSpot;
import com.xzlx.web.api.dao.TbScenicSpotDao;
import com.xzlx.web.api.service.TbScenicSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class TbScenicSpotServiceImpl implements TbScenicSpotService {

    @Autowired
    TbScenicSpotDao tbScenicSpotDao;

    @Override
    public BaseResult pageByAreaId(int areaId, int page, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("start", page*pageSize);
        map.put("length",pageSize);
        map.put("areaId",areaId);
        List<TbScenicSpot> tbScenicSpots = tbScenicSpotDao.pageByAreaId(map);
        BasePageDTO<TbScenicSpot> pageDTO = new BasePageDTO<>();
        Integer totalPage = tbScenicSpotDao.count(areaId);
        pageDTO.setPage(page);
        pageDTO.setPageSize(pageSize);
        pageDTO.setTotalPage(totalPage);
        BaseResult baseResult = null;
        if(tbScenicSpots.size()>0){
            pageDTO.setEntity(tbScenicSpots);
            baseResult = BaseResult.success("查询成功",pageDTO);
        }
        else{
            baseResult = BaseResult.fail("查询失败");
        }
        return baseResult;
    }
}
