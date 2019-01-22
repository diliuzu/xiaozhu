package com.xzlx.web.api.service.impl;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.commons.util.persistance.BasePageDTO;
import com.xzlx.domain.TbHotelType;
import com.xzlx.web.api.dao.TbHotalDao;
import com.xzlx.web.api.service.TbHotalService;
import com.xzlx.web.api.web.dto.TbHotelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/18 14:42
 * @Version 1.0
 */
@Service
public class TbHotalServiceImpl implements TbHotalService {
    @Autowired
    private TbHotalDao dao;

    @Override
    public BaseResult selectByAreaId(int id, int page, int pageSize) {
        Map<String,Object> map = new HashMap<>();
        map.put("start",(page-1)*pageSize);
        map.put("pageSize",pageSize);
        map.put("areaId",id);
        List<TbHotelDTO> tbHotelDTOS = dao.selectByAreaId(map);
        for (TbHotelDTO tbHotelDTO : tbHotelDTOS) {
            double price = dao.selectMinPrice(tbHotelDTO.getId());
            tbHotelDTO.setPrice(price);
        }
        BasePageDTO<TbHotelDTO> pageDTO = new BasePageDTO<>();
        pageDTO.setPage(page);
        pageDTO.setPageSize(pageSize);
        pageDTO.setTotalPage(dao.selectByAreaIdCount(id));
        pageDTO.setEntity(tbHotelDTOS);
        BaseResult baseResult =BaseResult.success("成功",pageDTO);
        return baseResult;
    }

    @Override
    public BaseResult selectType() {
        BaseResult baseResult = null;
        List<TbHotelType> tbHotelTypes = dao.selectType();
        baseResult = BaseResult.success("成功",tbHotelTypes);
        return baseResult;
    }

    @Override
    public BaseResult selectByTypeId(int typeId, int page, int pageSize) {
        Map<String,Object> map = new HashMap<>();
        map.put("start",(page-1)*pageSize);
        map.put("pageSize",pageSize);
        map.put("typeId",typeId);
        List<TbHotelDTO> tbHotelDTOS = dao.selectByTypeId(map);
        for (TbHotelDTO tbHotelDTO : tbHotelDTOS) {
            double price = dao.selectMinPrice(tbHotelDTO.getId());
            tbHotelDTO.setPrice(price);
        }
        BasePageDTO<TbHotelDTO> pageDTO = new BasePageDTO<>();
        pageDTO.setPage(page);
        pageDTO.setPageSize(pageSize);
        pageDTO.setTotalPage(dao.selectByTypeIdCount(typeId));
        pageDTO.setEntity(tbHotelDTOS);
        BaseResult baseResult =BaseResult.success("成功",pageDTO);
        return baseResult;
    }

    @Override
    public BaseResult selectById(int id) {
        List<TbHotelDTO> tbHotelDTOS = dao.selectById(id);
        for (TbHotelDTO tbHotelDTO : tbHotelDTOS) {
            double price = dao.selectMinPrice(tbHotelDTO.getId());
            tbHotelDTO.setPrice(price);
        }
        BaseResult baseResult =BaseResult.success("成功",tbHotelDTOS);
        return baseResult;
    }
}
