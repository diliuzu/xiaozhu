package com.xzlx.web.api.service.impl;

import com.xzlx.commons.dto.BaseResult;
import com.xzlx.domain.TbStrategy;
import com.xzlx.web.api.dao.TbStrategyDao;
import com.xzlx.web.api.service.TbStrategyService;
import com.xzlx.web.api.web.dto.TbStrategyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbStrategyServiceImpl implements TbStrategyService{
        @Autowired
        TbStrategyDao tbStrategyDao;

         @Override
         public int delete(int strategy_id) {
                return tbStrategyDao.delete(strategy_id);

         }

    /**
     * 查找攻略
     * @param strategy_id
     * @return
     */
    @Override
    public TbStrategyDTO selectById(int strategy_id, Integer login_user_id) {
        TbStrategyDTO tbStrategyDTO = tbStrategyDao.selectById(strategy_id, login_user_id);
        return tbStrategyDTO;
    }


    /**
     * 攻略详情
     * @param paramMap
     * @return
     */
    @Override
    public Map<String, Object> selectByCondition(Map<String, Object> paramMap) {

        int totalRow = tbStrategyDao.count(paramMap);
        int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());

        List<TbStrategyDTO> tbStrategyDTOS = tbStrategyDao.selectPageByCondition(paramMap);
        Map<String, Object> pageMap = new HashMap<>();
        //pageMap.put("totalPage",totalRow%pageSize==0?totalRow/pageSize:(totalRow/pageSize)+1);
        pageMap.put("totalPage",(totalRow+pageSize-1)/pageSize);
        pageMap.put("entity",tbStrategyDTOS);
        return pageMap;
    }

    @Override
    public void save(TbStrategy tbStrategy) {
        tbStrategyDao.insert(tbStrategy);
    }
}
