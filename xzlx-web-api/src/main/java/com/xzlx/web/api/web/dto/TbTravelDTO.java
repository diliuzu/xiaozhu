package com.xzlx.web.api.web.dto;

import lombok.Data;

@Data
public class TbTravelDTO {

    private Integer id;

    private String title;

    private UserDTO author;

    private String describe;

    private Integer clicknum;

    private Integer collectnum;

    private Integer hot;

    private String image;

    private Integer praisenum;

    private Boolean isPraised;

    private Boolean isCollected;

}
