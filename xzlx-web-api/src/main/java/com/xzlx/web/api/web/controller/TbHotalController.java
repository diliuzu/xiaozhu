package com.xzlx.web.api.web.controller;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.web.api.service.TbHotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/18 14:43
 * @Version 1.0
 */
@RestController
@RequestMapping("api/hotels")
public class TbHotalController {
    @Autowired
    private TbHotalService service;

    @RequestMapping(value = "areas/{area_id}" ,method = RequestMethod.GET)
    public BaseResult selectByAreaId(@PathVariable int area_id,int page,int pageSize){
        BaseResult baseResult = service.selectByAreaId(area_id,page,pageSize);

        return baseResult;
    }

    @RequestMapping(value = "types" ,method = RequestMethod.GET)
    public BaseResult selectType(){
        BaseResult baseResult = service.selectType();
        return baseResult;
    }

    @RequestMapping(value = "hoteltypes/{hotel_type_id}" ,method = RequestMethod.GET)
    public BaseResult selectByTypeId(@PathVariable int hotel_type_id,int page,int pageSize){
        BaseResult baseResult = service.selectByTypeId(hotel_type_id,page,pageSize);
        return baseResult;
    }

    @RequestMapping(value = "{hotel_id}" ,method = RequestMethod.GET)
    public BaseResult selectById(@PathVariable int hotel_id){
        BaseResult baseResult = service.selectById(hotel_id);
        return baseResult;
    }

}
