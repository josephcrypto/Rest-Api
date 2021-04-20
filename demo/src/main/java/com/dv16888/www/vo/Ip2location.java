package com.dv16888.www.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
public class Ip2location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String start_ip;
    private String end_ip;
    private Long startIpLong;
    private Long endIpLong;
    private String country;
    private String province;
    private String city;
    private String isp;
}
