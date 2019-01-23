package com.xzlx.web.api.web.controller;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.web.api.service.TbAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/areas")
public class TbAreaController {
    @Autowired
    TbAreaService tbAreaService;

    @RequestMapping(value = "{length}/{parentId}",method = RequestMethod.GET)
    public BaseResult page(@PathVariable("length")int length,@PathVariable("parentId")int parentId){
        return tbAreaService.selectByPage(length,parentId);
    }
    @RequestMapping(value = "all/{parentId}",method = RequestMethod.GET)
    public BaseResult areaAll(@PathVariable("parentId")int parentId){
        return tbAreaService.selectAll(parentId);
    }

}
