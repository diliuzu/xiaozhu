package com.xzlx.web.api.dao;

import com.xzlx.domain.TbStrategy;
import com.xzlx.web.api.web.dto.TbStrategyDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbStrategyDao {

    /**
     * 删除攻略
     * @param strategy_id
     * @return
     */
    int delete(int strategy_id);

    /**
     * 查找攻略
     * @param strategy_id
     * @return
     */
    TbStrategyDTO selectById(@Param("strategy_id")int strategy_id, @Param("login_user_id")Integer login_user_id);

    /**
     * 根据条件查询总条数
     * @param paramMap
     * @return
     */
    int count(Map<String, Object> paramMap);


    List<TbStrategyDTO> selectPageByCondition(Map<String, Object> paramMap);

    /**
     * 新增
     * @param tbStrategy
     */
    void insert(TbStrategy tbStrategy);
}
