package com.xzlx.web.api.web.controller;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.web.api.service.TbScenicSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/scenicspot")
public class TbScenicSpotController {

    @Autowired
    TbScenicSpotService tbScenicSpotService;

    @RequestMapping(value ="areas/{area_id}/{page}/{pageSize}",method = RequestMethod.GET)
    public BaseResult pageByAreaId(@PathVariable("area_id")int id, @PathVariable("page")int page, @PathVariable("pageSize")int pageSize){

        BaseResult baseResult = tbScenicSpotService.pageByAreaId(id, page, pageSize);
        return baseResult;
    }
}
