package com.xzlx.web.api.service.impl;

import com.xzlx.commons.util.persistance.BasePageDTO;
import com.xzlx.web.api.dao.TbFoodDAO;
import com.xzlx.web.api.service.TbFoodService;
import com.xzlx.web.api.web.dto.FoodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbFoodServiceImpl implements TbFoodService {

    @Autowired
    TbFoodDAO tbFoodDAO;

    @Override
    public BasePageDTO<FoodDTO> getFoods(Integer areaId, BasePageDTO<FoodDTO> basePageDTO) {
        basePageDTO.setEntity(tbFoodDAO.selectFoods(areaId,(basePageDTO.getPage()-1)*basePageDTO.getPageSize(),basePageDTO.getPageSize()));
        basePageDTO.setTotalPage((int) Math.ceil(tbFoodDAO.selectFoodsCount(areaId)*1.0/basePageDTO.getPageSize()));
        return basePageDTO;
    }
}
