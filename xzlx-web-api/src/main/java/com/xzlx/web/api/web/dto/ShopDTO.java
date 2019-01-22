package com.xzlx.web.api.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShopDTO {

    private Integer id;

    private String name;

    private String describe;

    private String image;

    private String addr;

    private Integer areaId;

    private Integer level;

    private List<Shop> shop;
}
