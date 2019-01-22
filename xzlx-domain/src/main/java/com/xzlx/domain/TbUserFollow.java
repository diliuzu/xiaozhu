package com.xzlx.domain;

import lombok.Data;

@Data
public class TbUserFollow {
    private Integer id;
    //关注者id
    private Integer followerId;
    //被关注者id
    private Integer edFollowerId;
}
