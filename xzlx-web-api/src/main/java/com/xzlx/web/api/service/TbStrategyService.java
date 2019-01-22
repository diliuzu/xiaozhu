package com.xzlx.web.api.service;

import com.xzlx.domain.TbStrategy;
import com.xzlx.web.api.web.dto.TbStrategyDTO;

import java.util.List;
import java.util.Map;

public interface TbStrategyService {
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
    TbStrategyDTO selectById(int strategy_id, Integer u);

    /**
     * 分页查询
     * @param param
     * @return
     */
    Map<String, Object> selectByCondition(Map<String, Object> param);

    /**
     * 新增
     * @param tbStrategy
     */
    void save(TbStrategy tbStrategy);

}
