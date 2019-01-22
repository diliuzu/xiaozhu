package com.xzlx.web.api.web.dto;

import com.xzlx.domain.TbHotelComment;
import com.xzlx.domain.TbHotelInstallation;
import lombok.Data;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/21 9:04
 * @Version 1.0
 */
@Data
public class TbHotelDTO {
    private int id;
    private String name;
    private String englishname;
    private String addr;
    private int score;
    private String describe;
    private int hotelTypeId;
    private int level;
    private String leaveTime;
    private String inTime;
    private String image;
    private double price;
    private TbHotelInstallation installation;
}
