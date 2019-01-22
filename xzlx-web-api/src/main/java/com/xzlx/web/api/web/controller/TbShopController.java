package com.xzlx.web.api.web.controller;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.commons.util.persistance.BasePageDTO;
import com.xzlx.web.api.service.TbShopService;
import com.xzlx.web.api.web.dto.ShopDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/shops")
public class TbShopController {

    @Autowired
    TbShopService tbShopService;


    @GetMapping("areas/{area_id}")
    public BaseResult getShops(@PathVariable(value = "area_id") Integer areaId, BasePageDTO<ShopDTO> basePageDTO){
        basePageDTO=tbShopService.getShopParents(areaId,basePageDTO);
        return BaseResult.success(200,"成功",basePageDTO);
    }

    @GetMapping("{shop_id}")
    public BaseResult getShop(@PathVariable(value = "shop_id") Integer shopId){
        return tbShopService.getShopById(shopId);

    }

}
