package com.xzlx.web.api.web.controller;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.commons.util.persistance.BasePageDTO;
import com.xzlx.web.api.service.TbFoodService;
import com.xzlx.web.api.web.dto.FoodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/foods")
public class TbFoodController {

    @Autowired
    TbFoodService tbFoodService;

    @GetMapping("areas/{area_id}")
    public BaseResult getFoods(@PathVariable(value = "area_id") Integer areaId, BasePageDTO<FoodDTO> basePageDTO){
        basePageDTO=tbFoodService.getFoods(areaId,basePageDTO);
        return BaseResult.success(200,"成功",basePageDTO);
    }

}

