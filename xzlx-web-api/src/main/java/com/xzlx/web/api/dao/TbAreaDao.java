package com.xzlx.web.api.dao;

import com.xzlx.domain.TbArea;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbAreaDao {

    List<TbArea> selectByPage(int length, int parentId);

    List<TbArea> selectAll(int parentId);

}
