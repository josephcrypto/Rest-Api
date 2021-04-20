package com.dv16888.www.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "chat_customerservice")
public class Customerservice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer csid;
    private String username;
    private String password;
    private Byte active;
    private String salt;
    private String realname;
}
