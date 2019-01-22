package com.xzlx.web.api.web.dto;

import lombok.Data;

@Data
public class Shop {

    private Integer id;

    private String name;

    private String describe;

    private String image;

    private String addr;

    private Integer areaId;

    private Integer level;
}
