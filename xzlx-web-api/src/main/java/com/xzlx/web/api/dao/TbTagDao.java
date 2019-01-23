package com.xzlx.web.api.dao;

import com.xzlx.domain.TbTag;
import com.xzlx.domain.TbTaglib;
import com.xzlx.domain.TbTravel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbTagDao {

    Integer count(TbTag tbTag);

    List<TbTag> selectByTagId(TbTag tbTag);

    List<TbTaglib> selectByTargetId(TbTravel tbTravel);
}
