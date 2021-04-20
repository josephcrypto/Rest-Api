package com.dv16888.www.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "chat_records")
public class Records {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rid;
    private Integer customerid;
    private Integer csid;
    @Column(name="content",columnDefinition="blob")
    private byte[] content;
    private Date chattime;
    private Byte read;
    private String ipaddress;
}
