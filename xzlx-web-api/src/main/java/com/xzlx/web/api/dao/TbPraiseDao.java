package com.xzlx.web.api.dao;

import com.xzlx.domain.TbPraise;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbPraiseDao {

    List<TbPraise> selectByUids(Map<String, Object> map);

    TbPraise selectByUid(Map<String, Object> map);

}
