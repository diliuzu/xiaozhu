package com.xzlx.web.api.web.dto;

import com.xzlx.domain.TbLine;
import lombok.Data;

import java.util.List;

@Data
public class TbLineDTO {

    private Integer id;

    private String describe;

    private List<Line> line;


}

