package com.xzlx.web.api.service;

import com.xzlx.commons.util.persistance.BasePageDTO;
import com.xzlx.web.api.web.dto.FoodDTO;

public interface TbFoodService {
    BasePageDTO<FoodDTO> getFoods(Integer areaId, BasePageDTO<FoodDTO> basePageDTO);
}
