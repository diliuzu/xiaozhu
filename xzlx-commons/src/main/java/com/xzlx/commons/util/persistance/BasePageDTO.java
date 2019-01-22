package com.xzlx.commons.util.persistance;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public  class BasePageDTO<T> implements Serializable {

    private Integer page;

    private Integer pageSize;

    private Integer totalPage;

    private List<T> entity;

}
