package com.xzlx.web.api.web.controller;
import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.domain.TbCollection;
import com.xzlx.domain.TbUser;
import com.xzlx.web.api.service.TbUserService;
import com.xzlx.web.api.web.dto.TbUserDTO;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/users") //http://localhost:8082/api/v1/users
public class TbUserController {
    @Autowired
         private TbUserService tbUserService;
        //http://localhost:8080/api/v1/users/login
    @RequestMapping(value = "login",method = RequestMethod.POST)//登录 name
    public BaseResult login(TbUser tbUser,HttpServletRequest request){
        BaseResult baseResult= tbUserService.login(tbUser);
        if (baseResult.getStatus()==200){
            TbUserDTO tbUserDTO= (TbUserDTO) baseResult.getData();
            TbUser user=new TbUser();
            BeanUtils.copyProperties(tbUserDTO,user);
            request.getSession().setAttribute("user",user);
        }
        return baseResult;
    }


    //register注册
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public BaseResult register(TbUser tbUser){
        BaseResult baseResult =tbUserService.register(tbUser);
        return  baseResult;
    }

  //忘记密码,发送邮件获取验证码
    @RequestMapping(value = "forgetpassword",method = RequestMethod.GET)
    public  BaseResult forgetpassword(TbUser tbUser, HttpServletRequest httpServletRequest)  {
        BaseResult baseResult = null;
        try {
            baseResult = tbUserService.forgetpassword(tbUser,httpServletRequest);
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return baseResult;
    }
  //忘记密码,校验验证码
    @RequestMapping(value = "checkcode",method = RequestMethod.POST)
    public  BaseResult checkvacode(String code,HttpServletRequest httpServletRequest){
        BaseResult baseResult = tbUserService.checkcode(code, httpServletRequest);
        return baseResult ;
    }
    //个人中心(除关注,粉丝,我的游记,收藏,攻略)
    @RequestMapping(value = "personal",method = RequestMethod.GET)
    public  BaseResult personal(TbUser tbUser){
        BaseResult basereult = tbUserService.personal(tbUser);
        return basereult;
    }
//重置密码
    @RequestMapping(value = "resetpassword",method = RequestMethod.POST)
    public  BaseResult resetpassword(TbUser tbUser){
        BaseResult baseResult = tbUserService.resetpassword(tbUser);
        return baseResult ;
    }
//编辑个人资料
    @RequestMapping(value = "edit",method = RequestMethod.POST)
    public BaseResult edit(TbUser tbUser){
        BaseResult baseResult = tbUserService.edit(tbUser);
        return baseResult;
        }
        //我的收藏
    @RequestMapping(value = "mycollection",method = RequestMethod.GET)
    public BaseResult mycollection(int id,int typeid){
        List<TbCollection> tbCollections = tbUserService.mycollection(id, typeid);
        return  BaseResult.success(200,"成功",tbCollections);
    }
    //游记收藏
    @GetMapping("collect/travels/{travel_id}")
    public BaseResult travels(@PathVariable(value = "travel_id") Integer travelId,HttpServletRequest request){
        TbUser user= (TbUser) request.getSession().getAttribute("user");
        user=new TbUser();
        user.setId(2);
        if(user==null)
            return BaseResult.fail(401,"该操作需要登录");
        else {
            return tbUserService.collect(travelId, user.getId(), 1);
        }
    }

    //收藏攻略
    @GetMapping("collect/strategies/{strategy_id}")
    public BaseResult strategies(@PathVariable(value = "strategy_id") Integer strategyId,HttpServletRequest request){
        TbUser user= (TbUser) request.getSession().getAttribute("user");
        user=new TbUser();
        user.setId(2);
        if(user==null)
            return BaseResult.fail(401,"该操作需要登录");
        else {
            return tbUserService.collect(strategyId, user.getId(), 2);
        }
    }

    //游记点赞
    @GetMapping("praise/travels/{travel_id}")
    public BaseResult praiseTravels(@PathVariable(value = "travel_id") Integer travelId,HttpServletRequest request){
        TbUser user= (TbUser) request.getSession().getAttribute("user");
        user=new TbUser();
        user.setId(2);
        if(user==null)
            return BaseResult.fail(401,"该操作需要登录");
        else {
            return tbUserService.praise(travelId, user.getId(), 1);
        }
    }

    //攻略点赞
    @GetMapping("praise/strategies/{strategy_id}")
    public BaseResult praiseStrategies(@PathVariable(value = "strategy_id") Integer strategyId,HttpServletRequest request){
        TbUser user= (TbUser) request.getSession().getAttribute("user");
        user=new TbUser();
        user.setId(2);
        if(user==null)
            return BaseResult.fail(401,"该操作需要登录");
        else {
            return tbUserService.praise(strategyId, user.getId(), 2);
        }
    }


}


