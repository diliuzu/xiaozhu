package com.xzlx.web.api.service;

import com.xzlx.commons.util.dto.BaseResult;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/18 14:42
 * @Version 1.0
 */
public interface TbHotalService {
    BaseResult selectByAreaId(int id, int page, int pageSize);
    BaseResult selectType();
    BaseResult selectByTypeId(int typeId, int page, int pageSize);
    BaseResult selectById(int id);

}
