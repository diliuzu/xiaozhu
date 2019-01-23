package com.xzlx.web.api.dao;

import com.xzlx.domain.TbCollection;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbCollectionDao {

    List<TbCollection> selectByUids(Map<String, Object> map);

    TbCollection selectByUid(Map<String, Object> map);
}
