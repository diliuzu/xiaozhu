package com.xzlx.web.api.web.controller;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.domain.TbTag;
import com.xzlx.domain.TbTravel;
import com.xzlx.domain.TbUser;
import com.xzlx.web.api.service.TbTravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@CrossOrigin
@RestController
@RequestMapping("api/v1/travels")
public class TbTravelController {

    @Autowired
    TbTravelService tbTravelService;

    //详情
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public BaseResult selectById(@PathVariable("id") int id,HttpServletRequest request){
        TbUser user = (TbUser) request.getSession().getAttribute("user");
        BaseResult baseResult = tbTravelService.selectById(id,user);
        return baseResult;
    }

    //删除
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public BaseResult deleteById(@PathVariable("id")int id){
        BaseResult baseResult = tbTravelService.delete(id);
        return baseResult;
    }

    //更新
    @RequestMapping(value = "update", method = RequestMethod.PATCH)
    public BaseResult update(TbTravel tbTravel){
        BaseResult baseResult = tbTravelService.save(tbTravel);
        return baseResult;
    }

    //添加
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public BaseResult add(TbTravel tbTravel, HttpServletRequest request){
//        TbUser user = (TbUser)request.getSession().getAttribute("user");
        TbUser user = new TbUser();
        user.setId(1);
        tbTravel.setAuthor(user.getId());
        tbTravel.setAreaId(1);
        BaseResult baseResult = tbTravelService.save(tbTravel);
        return baseResult;
    }

    //按地区查询
    @RequestMapping(value = "areas/{area_id}", method = RequestMethod.GET)
    public BaseResult areas(@PathVariable("area_id")int id,int page,int pageSize,HttpServletRequest request){
        return areasAndAuthors(id,page,pageSize,request);
    }
    public BaseResult areasAndAuthors(int id, int page,int pageSize,HttpServletRequest request){
        TbTravel tbTravel = new TbTravel();
        tbTravel.setAreaId(id);
        TbUser user = (TbUser) request.getSession().getAttribute("user");
        BaseResult baseResult = tbTravelService.page(page, pageSize, tbTravel,user);
        return baseResult;
    }
    //按作者查询
    @RequestMapping(value = "authors/{authors_id}", method = RequestMethod.GET)
    public BaseResult authors(@PathVariable("authors_id")int id,int page,int pageSize,HttpServletRequest request){
        return areasAndAuthors(id,page,pageSize,request);
    }

    //按标签
    @RequestMapping(value = "tags/{tags_id}", method = RequestMethod.GET)
    public BaseResult tags(@PathVariable("tags_id")int id,int page,int pageSize,HttpServletRequest request){
        TbTag tbTag = new TbTag();
        tbTag.setTaglibId(1);
        tbTag.setTypeId(1);
        TbUser user = (TbUser) request.getSession().getAttribute("user");
        BaseResult baseResult = tbTravelService.pageByTag(page,pageSize,tbTag,user);
        return baseResult;
    }

}
