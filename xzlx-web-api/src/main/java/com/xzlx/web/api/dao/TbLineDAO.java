package com.xzlx.web.api.dao;

import com.xzlx.web.api.web.dto.Line;
import com.xzlx.web.api.web.dto.TbLineDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbLineDAO {
    List<TbLineDTO> selectStrategyId(@Param("id") Integer id, @Param("start")Integer start, @Param("length")Integer length);

    Integer selectStrategyIdCount(Integer id);

    List<Line> selectLines(Integer id);
}
