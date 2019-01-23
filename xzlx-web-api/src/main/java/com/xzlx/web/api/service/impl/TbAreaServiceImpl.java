package com.xzlx.web.api.service.impl;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.domain.TbArea;
import com.xzlx.web.api.dao.TbAreaDao;
import com.xzlx.web.api.service.TbAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbAreaServiceImpl implements TbAreaService {

    @Autowired
    TbAreaDao tbAreaDao;

    @Override
    public BaseResult selectByPage(int length,int parentId) {
        List<TbArea> tbAreas = tbAreaDao.selectByPage(length,parentId);
        BaseResult baseResult = null;
        if(tbAreas!=null){
            baseResult = BaseResult.success("获取成功",tbAreas);
        }
        else {
            baseResult = BaseResult.fail("获取失败");
        }
        return baseResult;
    }

    @Override
    public BaseResult selectAll(int parentId) {
        List<TbArea> tbAreas = tbAreaDao.selectAll(parentId);
        BaseResult baseResult = null;
        if(tbAreas!=null){
            baseResult = BaseResult.success("获取成功",tbAreas);
        }
        else {
            baseResult = BaseResult.fail("获取失败");
        }
        return baseResult;
    }
}
