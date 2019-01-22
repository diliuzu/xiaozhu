package com.xzlx.web.api.service.impl;

import com.xzlx.commons.util.persistance.BasePageDTO;
import com.xzlx.domain.TbHotel;
import com.xzlx.web.api.dao.TbCommentDAO;
import com.xzlx.web.api.service.TbCommentService;
import com.xzlx.web.api.web.dto.TbCommentDTO;
import com.xzlx.web.api.web.dto.TbCommentTables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.stream.events.Comment;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional(readOnly = true)
public class TbCommentServiceImpl implements TbCommentService {

    @Autowired
    TbCommentDAO tbCommentDAO;

    private int start;

    private int length;

    @Override
    public BasePageDTO<TbCommentDTO> getComment(Integer id, BasePageDTO<TbCommentDTO> basePageDTO,String tbName) {
        initLimit(basePageDTO);
        basePageDTO.setEntity(tbCommentDAO.selectComment(id,start,length,tbName));
        basePageDTO.setTotalPage((int) Math.ceil(tbCommentDAO.selectCommentCounts(id,tbName)*1.0/basePageDTO.getPageSize()));

        return basePageDTO;
    }

    @Override
    @Transactional(readOnly = false)
    public void addComment(Integer travelId, TbCommentDTO tbCommentDTO, String travel) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        tbCommentDTO.setCreated(simpleDateFormat.format(new Date()));


        tbCommentDAO.insert(travelId,tbCommentDTO,travel,getTargetName(travel));
    }


    private  void initLimit(BasePageDTO<TbCommentDTO> basePageDTO){
       this.start=(basePageDTO.getPage()-1)*basePageDTO.getPageSize();
       this.length=basePageDTO.getPageSize();
    }

    private String getTargetName(String target){
        switch (target){
            case TbCommentTables.Travel:return "travel_id";
            case TbCommentTables.Hotel:return "hotel_id";
            case TbCommentTables.Strategy:return "strategy_id";
            default:return null;
        }
    }
}
