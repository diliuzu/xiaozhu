package com.xzlx.web.api.web.controller;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.web.api.service.TbUserPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/photo")
public class TbUserPhotoController {
    @Autowired
    private TbUserPhotoService tbUserPhotoService;
   @RequestMapping(value = "getphoto",method = RequestMethod.GET)
    public BaseResult getphoto(){
       BaseResult baseResult = tbUserPhotoService.getphoto();
       return baseResult;
    }
}
