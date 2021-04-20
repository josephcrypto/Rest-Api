package com.dv16888.www.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "chat_visit")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer visitid;
    private String customerid;
    private Date created_at;

    @Column( columnDefinition = "json" )
    private String content;
}
