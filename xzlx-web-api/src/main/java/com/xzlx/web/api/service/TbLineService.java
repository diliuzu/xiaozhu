package com.xzlx.web.api.service;

import com.xzlx.commons.util.persistance.BasePageDTO;
import com.xzlx.web.api.dao.TbLineDAO;
import com.xzlx.web.api.web.dto.Line;
import com.xzlx.web.api.web.dto.TbCommentDTO;
import com.xzlx.web.api.web.dto.TbLineDTO;

import java.util.List;

public interface TbLineService {
    BasePageDTO<TbLineDTO> getLinesByAreaId(Integer areaId, BasePageDTO<TbLineDTO> basePageDTO);

    List<Line> getLinesByStrategyId(Integer strategyId);
}
