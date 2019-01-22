package com.xzlx.web.api.web.controller;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.web.api.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/22 9:19
 * @Version 1.0
 */
@RestController
@RequestMapping("api")
public class SearchController {
    @Autowired
    private SearchService service;

    @RequestMapping(value = "search")
    public BaseResult Search(String kind,String content){
        BaseResult baseResult = service.search(kind,content);
        return baseResult;
    }
}
