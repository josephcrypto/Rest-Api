package com.dv16888.www.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "chat_ip2locations")
public class Ip2locations {
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
