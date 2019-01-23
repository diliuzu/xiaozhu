package com.xzlx.web.api.dao;

import com.xzlx.commons.util.persistance.BaseDAO;
import com.xzlx.domain.TbTravel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbTravelDao extends BaseDAO<TbTravel> {

    List<TbTravel> selectByPage(Map<String, Object> map);

    Integer count(TbTravel tbTravel);

    List<TbTravel> selectByTag(Map<String, Object> map);

}
