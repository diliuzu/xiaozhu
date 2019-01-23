package com.xzlx.web.api.web.controller;


import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.domain.TbUser;
import com.xzlx.web.api.service.TbUserFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/usersfollow")
public class TbUserFollowController {
@Autowired
   private TbUserFollowService  tbUserFollowService;



    //关注他人,取消关注
    @GetMapping("{user_id}")
    public BaseResult addFollow(@PathVariable(value = "user_id") Integer userId, HttpServletRequest request){
        TbUser tbUser= (TbUser) request.getSession().getAttribute("user");
        tbUserFollowService.followerUser(userId,tbUser);
        return BaseResult.success();
    }
}
