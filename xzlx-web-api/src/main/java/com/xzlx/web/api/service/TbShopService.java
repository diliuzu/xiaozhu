package com.xzlx.web.api.service;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.commons.util.persistance.BasePageDTO;
import com.xzlx.web.api.web.dto.ShopDTO;

public interface TbShopService {
    BasePageDTO<ShopDTO> getShopParents(Integer areaId, BasePageDTO<ShopDTO> basePageDTO);

    BaseResult getShopById(Integer shopId);
}
