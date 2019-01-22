package com.xzlx.domain;

import lombok.Data;

import java.util.Date;

@Data
public class TbTravel {
    private Integer id;

    private String title;

    private Integer official;

    private String image;

    private Integer author;

    private Integer areaId;

    private Date created;

    private Date updated;

    private Integer collectnum;

    private Integer praisenum;

    private Integer clicknum;

    private Integer hot;

    private String describe;

    private String content;



}