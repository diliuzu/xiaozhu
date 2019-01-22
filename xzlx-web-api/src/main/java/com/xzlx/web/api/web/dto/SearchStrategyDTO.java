package com.xzlx.web.api.web.dto;

import com.xzlx.domain.TbTaglib;
import com.xzlx.domain.TbUser;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/22 11:03
 * @Version 1.0
 */
@Data
public class SearchStrategyDTO {
    private Integer id;
    private String title;
    private Integer day;
    private Integer official;
    private TbUser author;
    private List<TbTaglib> tags;
    private Date created;
    private Integer praisenum;
    private Integer collectnum;
    private Integer clicknum;
    private Integer hot;
    private String image;

}
