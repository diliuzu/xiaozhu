package com.xzlx.web.api.dao;

import com.xzlx.domain.TbScenicSpot;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbScenicSpotDao {

    List<TbScenicSpot> pageByAreaId(Map<String, Object> map);

    Integer count(int areaId);

    void add(TbScenicSpot tbScenicSpot);
}
