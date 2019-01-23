package com.xzlx.web.api.web.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PersonDTO {
    private Integer id;

    private String name;

    private String password;

    private String nickname;

    private Integer sex;

    private String phone;

    private String email;

    private Date created;

    private String photo;

    private Date updated;

    private Integer friends;

    private Integer level;

    private Integer vip;

    private String signature;

    private Integer follows;

    private Integer fans;
}
