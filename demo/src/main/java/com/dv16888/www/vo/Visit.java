package com.dv16888.www.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Visit {
    private Integer visitid;
    private String customerid;
    private Date created_at;
    private String content;
}
