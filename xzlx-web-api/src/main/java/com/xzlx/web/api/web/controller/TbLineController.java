package com.xzlx.web.api.web.controller;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.commons.util.persistance.BasePageDTO;
import com.xzlx.web.api.dao.TbLineDAO;
import com.xzlx.web.api.service.TbLineService;
import com.xzlx.web.api.web.dto.Line;
import com.xzlx.web.api.web.dto.TbLineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/lines")
public class TbLineController {

    @Autowired
    TbLineService tbLineService;


    @GetMapping("areas/{area_id}")
    public BaseResult getLinesByAreaId(@PathVariable(value = "area_id") Integer areaId,BasePageDTO<TbLineDTO> basePageDTO){
        basePageDTO=tbLineService.getLinesByAreaId(areaId,basePageDTO);
        return  BaseResult.createBaseResult(200,"成功",basePageDTO);
    }

    @GetMapping("strategies/{strategy_id}")
    public BaseResult getLinesByStrategyId(@PathVariable(value = "strategy_id") Integer strategyId){
        List<Line> lines=tbLineService.getLinesByStrategyId(strategyId);
        return BaseResult.success(200,"成功",lines);
    }
}
