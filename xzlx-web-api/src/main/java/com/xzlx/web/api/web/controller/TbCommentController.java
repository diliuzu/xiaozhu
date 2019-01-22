package com.xzlx.web.api.web.controller;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.commons.util.persistance.BasePageDTO;
import com.xzlx.domain.TbUser;
import com.xzlx.web.api.service.TbCommentService;
import com.xzlx.web.api.web.dto.TbCommentDTO;
import com.xzlx.web.api.web.dto.TbCommentTables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/comments")
public class TbCommentController {

    @Autowired
    TbCommentService tbCommentService;

    @GetMapping("/travels/{travel_id}")
    public BaseResult getTravelComment(@PathVariable(value = "travel_id")Integer travelId,BasePageDTO<TbCommentDTO> basePageDTO){
        basePageDTO=tbCommentService.getComment(travelId,basePageDTO, TbCommentTables.Travel);
        return BaseResult.success(200,"成功",basePageDTO);
    }

    @GetMapping("/hotels/{hotel_id}")
    public BaseResult getHotelComment(@PathVariable(value = "hotel_id") Integer hotelId,BasePageDTO<TbCommentDTO> basePageDTO){
        basePageDTO=tbCommentService.getComment(hotelId,basePageDTO, TbCommentTables.Hotel);
        return BaseResult.success(200,"成功",basePageDTO);
    }

    @GetMapping("/strategies/{strategy_id}")
    public BaseResult getStrategyComment(@PathVariable(value = "strategy_id") Integer strategyId,BasePageDTO<TbCommentDTO> basePageDTO){
        basePageDTO=tbCommentService.getComment(strategyId,basePageDTO, TbCommentTables.Strategy);
        return BaseResult.success(200,"成功",basePageDTO);
    }

    @PostMapping("/travels/{travel_id}")
    public BaseResult setTravelComment(@PathVariable(value = "travel_id")Integer travelId, TbCommentDTO tbCommentDTO, HttpServletRequest request){
        TbUser user = (TbUser) request.getSession().getAttribute("user");
        tbCommentDTO.setUserDto(user.getId());
        tbCommentService.addComment(travelId,tbCommentDTO,TbCommentTables.Travel);
        return BaseResult.success("成功");
    }

    @PostMapping("/hotels/{hotel_id}")
    public BaseResult setHotelComment(@PathVariable(value = "hotel_id")Integer hotelId,TbCommentDTO tbCommentDTO,HttpServletRequest request){
        TbUser user = (TbUser) request.getSession().getAttribute("user");
        tbCommentDTO.setUserDto(user.getId());
        tbCommentService.addComment(hotelId,tbCommentDTO,TbCommentTables.Hotel);
        return BaseResult.success("成功");
    }

    @PostMapping("/strategies/{strategy_id}")
    public BaseResult setStrategyComment(@PathVariable(value = "strategy_id")Integer strategyId,TbCommentDTO tbCommentDTO,HttpServletRequest request){
        TbUser user = (TbUser) request.getSession().getAttribute("user");
        tbCommentDTO.setUserDto(user.getId());
        tbCommentService.addComment(strategyId,tbCommentDTO,TbCommentTables.Strategy);
        return BaseResult.success("成功");
    }
}
