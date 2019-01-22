package com.xzlx.web.api.web.controller;

import com.xzlx.commons.dto.BaseResult;
import com.xzlx.commons.util.ConstantUitls;
import com.xzlx.domain.TbStrategy;
import com.xzlx.domain.TbUser;
import com.xzlx.web.api.service.TbStrategyService;
import com.xzlx.web.api.web.dto.TbStrategyDTO;
import com.xzlx.web.api.web.dto.TbUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/strategies")
public class TbStrategyController {
    @Autowired
    TbStrategyService tbStrategyService;
    @RequestMapping(value = "{strategy_id}",method = RequestMethod.DELETE)
    public BaseResult delete(@PathVariable("strategy_id") int strategy_id){

        int result = tbStrategyService.delete(strategy_id);
        BaseResult baseResult = BaseResult.success(200, "删除成功", null);
        return baseResult;
    }

    /**
     * 攻略详情
     * @param strategy_id
     * @return
     */
    @RequestMapping(value = "{strategy_id}",method = RequestMethod.GET)
    public BaseResult find(@PathVariable("strategy_id")int strategy_id, HttpServletRequest request){
        TbUser tbUser = (TbUser) request.getSession().getAttribute(ConstantUitls.SESSION_USER);
        Integer login_user_id = null;
        if(tbUser!=null){
            login_user_id= tbUser.getId();
        } else{
            login_user_id =2;  // 测试用的
        }
        TbStrategyDTO tbStrategyDTO = tbStrategyService.selectById(strategy_id,login_user_id);
        BaseResult baseResult = BaseResult.success(200, "成功", tbStrategyDTO);
        return baseResult;
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public BaseResult save(TbStrategy tbStrategy, HttpServletRequest request){
        String authorId = request.getParameter("authorId");
        tbStrategy.setAuthor(Integer.parseInt(authorId));
        tbStrategyService.save(tbStrategy);
        BaseResult baseResult = BaseResult.success(200, "新增成功", null);
        return baseResult;
    }




    /**
     * 作者查询攻略
     * @param author_id
     * @return
     */
    @RequestMapping(value = "/authors/{author_id}",method = RequestMethod.GET)
    public BaseResult find(HttpServletRequest request, @PathVariable("author_id")int author_id){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("author_id",author_id);
        BaseResult baseResult =getCommonPageList(paramMap, request);
        return baseResult;
    }



    /**
     * 地区查询
     * @param area_id
     * @return
     */
    @RequestMapping(value = "areas/{area_id}",method = RequestMethod.GET)
    public BaseResult areas(HttpServletRequest request, @PathVariable("area_id")int area_id){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("area_id",area_id);
        BaseResult baseResult =getCommonPageList(paramMap, request);
        return baseResult;
    }

    /**
     * 标签查询
     * @param taglib_id
     * @return
     */
    @RequestMapping(value = "tags/{taglib_id}",method = RequestMethod.GET)
    public BaseResult tags(HttpServletRequest request, @PathVariable("taglib_id")int taglib_id){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("taglib_id",taglib_id);
        BaseResult baseResult =getCommonPageList(paramMap, request);
        return baseResult;
    }




    /**
     * 通用的查询代码,抽出
     * @param paramMap
     * @return
     */
    private BaseResult getCommonPageList(Map<String, Object> paramMap, HttpServletRequest request){
        String strPage = request.getParameter("page");
        String strPageSize = request.getParameter("pageSize");

        int page = strPage == null ? 1 : Integer.parseInt(strPage);
        int pageSize = strPageSize == null ? 10 : Integer.parseInt(strPageSize);


        paramMap.put("offset", (page-1)*pageSize);
        paramMap.put("pageSize",pageSize);
        Map<String, Object> pageAll= tbStrategyService.selectByCondition(paramMap);
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("page",page);
        pageMap.put("totalPage",pageAll.get("totalPage"));
        pageMap.put("entity", pageAll.get("entity"));
        BaseResult baseResult = BaseResult.success(200, "成功", pageMap);
        return  baseResult;
    }


}
