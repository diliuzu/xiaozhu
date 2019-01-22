package com.xzlx.web.api.web.dto;

import com.xzlx.domain.TbTaglib;
import com.xzlx.domain.TbUser;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
public class TbStrategyDTO {
    private Integer id;

    private String title;

    private String image;

    private Integer day;

    private Boolean official;



    private BigDecimal price;

    private Integer areaId;

    private Date created;

    private Integer praisenum;

    private Integer collectnum;

    private Integer clicknum;

    private Boolean hot;

    private String describes;

    private String content;

    private Boolean isPraised;
    private Boolean isCollected;


    private List<TbTaglib> tag;

    private TbUserDTO author;


}