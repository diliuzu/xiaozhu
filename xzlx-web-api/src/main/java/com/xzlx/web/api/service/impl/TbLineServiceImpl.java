package com.xzlx.web.api.service.impl;

import com.xzlx.commons.util.persistance.BasePageDTO;
import com.xzlx.web.api.dao.TbLineDAO;
import com.xzlx.web.api.service.TbLineService;
import com.xzlx.web.api.web.dto.Line;
import com.xzlx.web.api.web.dto.TbCommentDTO;
import com.xzlx.web.api.web.dto.TbLineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbLineServiceImpl  implements TbLineService {

    @Autowired
    TbLineDAO tbLineDAO;

    @Override
    public BasePageDTO<TbLineDTO> getLinesByAreaId(Integer areaId, BasePageDTO<TbLineDTO> basePageDTO) {

        int start=(basePageDTO.getPage()-1)*basePageDTO.getPageSize();
        basePageDTO.setEntity(tbLineDAO.selectStrategyId(areaId,start,basePageDTO.getPageSize()));
        basePageDTO.setTotalPage((int) Math.ceil(tbLineDAO.selectStrategyIdCount(areaId)*1.0/basePageDTO.getPageSize()));
        int length=basePageDTO.getEntity().size();
        for(int i=0;i<length;i++){
            basePageDTO.getEntity().get(i).setLine(tbLineDAO.selectLines(basePageDTO.getEntity().get(i).getId()));
        }
        return basePageDTO;
    }

    @Override
    public List<Line> getLinesByStrategyId(Integer strategyId) {
        return tbLineDAO.selectLines(strategyId);
    }
}
