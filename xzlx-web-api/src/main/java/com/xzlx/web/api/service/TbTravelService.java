package com.xzlx.web.api.service;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.domain.TbTag;
import com.xzlx.domain.TbTravel;
import com.xzlx.domain.TbUser;

public interface TbTravelService {

    BaseResult selectById(Integer id, TbUser user);

    BaseResult delete(Integer id);

    BaseResult save(TbTravel tbTravel);

    BaseResult page(int page, int pageSize, TbTravel tbTravel, TbUser user);

    BaseResult pageByTag(int page, int pageSize, TbTag tbTag, TbUser user);
}
