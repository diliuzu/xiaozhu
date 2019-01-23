package com.xzlx.web.api.service.impl;

import com.xzlx.commons.util.EmailUtils;
import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.domain.TbCollection;
import com.xzlx.domain.TbUser;
import com.xzlx.web.api.dao.TbUserDao;
import com.xzlx.web.api.dao.TbUserFollowDao;
import com.xzlx.web.api.service.TbUserService;
import com.xzlx.web.api.web.dto.PersonDTO;
import com.xzlx.web.api.web.dto.TbUserDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;
    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    TbUserFollowDao tbUserFollowDao;

    @Override    //登录
    public BaseResult login(TbUser tbUser) {
        BaseResult baseResult = null;
        TbUser user = tbUserDao.login(tbUser);
        //把tbuser中的密码和去数据库中查询过的密码对比
        String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
        if (password.equals(user.getPassword())) {  //验证成功
            TbUserDTO dto = new TbUserDTO();
            BeanUtils.copyProperties(user, dto);
            baseResult = baseResult.success("登录成功", dto);

        } else {
            baseResult = baseResult.fail("登录失败");
        }
        return baseResult;
    }

    //注册
//注册
    @Override
    public BaseResult register(TbUser tbUser) {
        Random random = new Random();
        BaseResult baseResult = checkUser(tbUser);
        if(tbUser.getEmail().equals(selectall().getEmail())){
            baseResult.fail("邮箱已经存在,请重新输入");
        }
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            tbUser.setEmail(tbUser.getEmail());
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            tbUser.setName("猪崽" + random.nextInt(2000 + 1));
            tbUser.setSex(1);
            tbUser.setFriends(0);
            tbUser.setLevel(0);
            tbUser.setNickname("小猪旅行" + RandomStringUtils.randomAlphanumeric(3));//+RandomStringUtils.randomAlphanumeric(3)
            tbUser.setVip(0);
            tbUser.setPhoto("/photo.jpg");
            tbUserDao.register(tbUser);
            baseResult = BaseResult.success("新增用户成功");
        }

        return  baseResult;
    }

    //忘记密码,发送邮件
    @Override
    public BaseResult forgetpassword(TbUser tbUser, HttpServletRequest httpServletRequest) throws EmailException {
        BaseResult baseResult = null;
        if (tbUser.getEmail() != null) {
            baseResult = baseResult.success("发送成功,请注意查收");
            emailUtils.send("验证信息", String.format("您的验证码是[ %s ] ,请尽快操作,为保证您的账号安全,请不要将验证码告知其他人,以免蒙受损失", RandomStringUtils.randomAlphanumeric(4)), tbUser.getEmail());
            httpServletRequest.getSession().setAttribute("code", RandomStringUtils.randomAlphanumeric(4));
        } else {
            baseResult = baseResult.fail("请输入正确的邮箱号码");
        }
        return baseResult;
    }

    //忘记密码,校验验证码
    @Override
    public BaseResult checkcode(String code, HttpServletRequest httpServletRequest) {
        BaseResult baseResult = null;
        String s = (String) httpServletRequest.getSession().getAttribute("code");
        if (code.equals(s)) {
            baseResult = baseResult.success("验证成功,请重新设置您的密码");
        } else {
            baseResult = baseResult.fail("验证失败,请重新验证");
        }
        return baseResult;
    }

    //重置密码
    @Override
    public BaseResult resetpassword(TbUser tbUser) {
        BaseResult baseResult = null;
        if (StringUtils.isBlank(tbUser.getPassword())) {
            baseResult = baseResult.fail("请输入正确的密码");
        } else {
            String s = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
            tbUser.setPassword(s);
            tbUserDao.resetpassword(tbUser);
            baseResult = baseResult.success("重置密码成功");
        }
        return baseResult;
    }

    @Override  //编辑个人资料
    public BaseResult edit(TbUser tbUser) {
        BaseResult baseResult = chekceditUser(tbUser);
        tbUserDao.edit(tbUser);
        baseResult=baseResult.success("修改成功");
        return  baseResult;
    }
//我的收藏
    @Override
    public List<TbCollection> mycollection(int id, int typeid) {
        Map<String,Object> map=new HashMap();
        map.put("id",id);
        map.put("typeid",typeid);
        List<TbCollection> tbCollections = tbUserDao.mycollection(map);

        return tbCollections;
    }



    //个人中心
    @Override
    public BaseResult personal(TbUser tbUser) {
        BaseResult baseResult = null;
        TbUser user = tbUserDao.personal(tbUser);
        if (user != null) {
            PersonDTO dto = new PersonDTO();
            BeanUtils.copyProperties(user, dto);
            dto.setFollows(tbUserFollowDao.personalshowfollow(tbUser.getId()));
            dto.setFans(tbUserFollowDao.followershow(tbUser.getId()));
            baseResult = BaseResult.success("成功", dto);
        }
        return baseResult;
    }


    public BaseResult checkUser(TbUser tbuser) {
        BaseResult baseResult = BaseResult.success();
        if (StringUtils.isBlank(tbuser.getEmail())) {
            baseResult = baseResult.fail("邮箱不能为空");
        } else if (StringUtils.isBlank(tbuser.getPassword())) {
            baseResult = baseResult.fail("密码不能为空");
        }
        return baseResult;
    }

    public BaseResult chekceditUser(TbUser tbUser) {
        BaseResult baseResult = BaseResult.success();
        if (StringUtils.isBlank(tbUser.getNickname())) {
            baseResult = baseResult.success("用户名不能为空,请重新设置");
            tbUser.setNickname("小猪旅行"+ RandomStringUtils.randomAlphanumeric(8));
        } else if (StringUtils.isBlank(tbUser.getSignature())){
            tbUser.setSignature("好好学习,天天向上");
            baseResult=baseResult.success();
        }
        return  baseResult;
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult collect(Integer travelId, Integer id, Integer type) {
        Integer count=tbUserDao.selectCollect(travelId,id,type);
        if (count==0){
            tbUserDao.addCollect(travelId,id,type);
            upCollect(travelId,1,type);
            return BaseResult.success("收藏成功");
        }else {
            tbUserDao.deleteCollect(travelId,id,type);
            downCollect(travelId,1,type);
            return BaseResult.success("取消收藏成功");
        }
    }
    @Override
    @Transactional(readOnly = false)
    public BaseResult praise(Integer travelId, Integer id, Integer type) {
        Integer count=tbUserDao.selectPraise(travelId,id,type);
        if (count==0){
            tbUserDao.addPraise(travelId,id,type);
            upCollect(travelId,2,type);
            return BaseResult.success("点赞成功");
        }else {
            tbUserDao.deletePraise(travelId,id,type);
            downCollect(travelId,2,type);
            return BaseResult.success("取消点赞成功");
        }
    }

    private void upCollect(Integer travelId, Integer id, Integer type){
        String table=type==1?"tb_travel":"tb_strategy";
        tbUserDao.upCollectCount(travelId,id,table);
    }
    private void downCollect(Integer travelId, Integer id, Integer type){
        String table=type==1?"tb_travel":"tb_strategy";
        tbUserDao.downCollectCount(travelId,id,table);
    }

    public  TbUser  selectall(){
        TbUser selectuser = tbUserDao.selectall();
        return selectuser;
    }
}