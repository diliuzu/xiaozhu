package com.xzlx.web.api.service;


import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.domain.TbUser;

public interface TbUserFollowService {


        BaseResult followerUser(Integer userId, TbUser tbUser);
}
