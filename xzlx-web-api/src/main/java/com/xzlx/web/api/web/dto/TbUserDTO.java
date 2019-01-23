package com.xzlx.web.api.web.dto;


import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;


@Data
public class TbUserDTO {
    private int id;
    private String nickname;
    private  int sex;
    private int friends;
    private int level;
    private  int vip;
@Ignore
    private  String password;
}
