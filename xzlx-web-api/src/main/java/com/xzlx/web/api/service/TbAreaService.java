package com.xzlx.web.api.service;

import com.xzlx.commons.util.dto.BaseResult;

public interface TbAreaService {

    BaseResult selectByPage(int length, int parentId);

    BaseResult selectAll(int parentId);

}
