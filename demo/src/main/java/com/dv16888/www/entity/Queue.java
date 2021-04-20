package com.dv16888.www.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "chat_queue")
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
