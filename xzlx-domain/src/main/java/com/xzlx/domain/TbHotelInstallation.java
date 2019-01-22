package com.xzlx.domain;

import lombok.Data;

@Data
public class TbHotelInstallation {

    private Integer hotelId;
    //有无wifi
    private Integer wifi;
    //有无停车场
    private Integer parking;
    //是否提供24小时服务
    private Integer hour24;
    //是否提供餐厅
    private Integer restaurant;
    //是否提供行李寄存
    private Integer baggage;
    //是否提供电梯
    private Integer elevator;
    //是否提供热水器
    private Integer kettle;
    //是否提供吹风机
    private Integer blower;


}