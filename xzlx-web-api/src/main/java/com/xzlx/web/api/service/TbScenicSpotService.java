package com.xzlx.web.api.service;

import com.xzlx.commons.util.dto.BaseResult;

public interface TbScenicSpotService {

    BaseResult pageByAreaId(int id, int page, int pageSize);
}
