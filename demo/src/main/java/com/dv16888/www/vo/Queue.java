package com.dv16888.www.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Queue {

    private Integer qid;
    private String customerid;
    private Date createdat;
    private Byte active;
    private Integer csid;
    private String ip;
    private String country;
    private String province;
    private String city;
    private String isp;

}
