package com.xzlx.web.api.service.impl;


import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.domain.TbUser;
import com.xzlx.web.api.dao.TbUserFollowDao;
import com.xzlx.web.api.service.TbUserFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbUserFollowServiceImpl  implements TbUserFollowService {
    @Autowired
    private TbUserFollowDao tbUserFollowDao;



    @Override
    public BaseResult followerUser(Integer userId, TbUser tbUser) {
        Integer count=tbUserFollowDao.select(userId,tbUser.getId());
        if(count==0){
            tbUserFollowDao.addFollow(userId,tbUser.getId());
            return BaseResult.success("关注成功");
        }else{
            tbUserFollowDao.removeFollow(userId,tbUser.getId());
            return BaseResult.success("取消关注成功");
        }
    }
}