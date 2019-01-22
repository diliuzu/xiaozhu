package com.xzlx.web.api.service.impl;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.commons.util.persistance.BasePageDTO;
import com.xzlx.web.api.dao.TbShopDAO;
import com.xzlx.web.api.service.TbShopService;
import com.xzlx.web.api.web.dto.ShopDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbShopServiceImpl implements TbShopService {

    @Autowired
    TbShopDAO tbShopDAO;

    @Override
    public BasePageDTO<ShopDTO> getShopParents(Integer areaId, BasePageDTO<ShopDTO> basePageDTO) {
        basePageDTO.setEntity(tbShopDAO.selectParentById(areaId,(basePageDTO.getPage()-1)*basePageDTO.getPageSize(),basePageDTO.getPageSize()));
        basePageDTO.setTotalPage(tbShopDAO.selectParentByIdCount(areaId));
        int length=basePageDTO.getEntity().size();
        for(int i=0;i<length;i++){
            basePageDTO.getEntity().get(i).setShop(tbShopDAO.selectByParentId(basePageDTO.getEntity().get(i).getId()));
        }
        return basePageDTO;
    }

    @Override
    public BaseResult getShopById(Integer shopId) {
        return BaseResult.success(200,"成功",tbShopDAO.selectById(shopId));
    }
}
