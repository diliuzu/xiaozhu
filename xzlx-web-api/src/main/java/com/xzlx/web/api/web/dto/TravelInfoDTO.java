package com.xzlx.web.api.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xzlx.domain.TbTaglib;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TravelInfoDTO {

    private String title;

    private UserInfoDTO author;

    private List<TbTaglib> tag;

    private Integer official;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss ")
    private Date created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updated;

    private Integer clicknum;

    private Integer collectnum;

    private Integer hot;

    private Integer praisenum;

    private Boolean isPraised;

    private Boolean isCollected;

}

