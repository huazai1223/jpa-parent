package com.jiadonghua.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "hg_drivercard")
public class DriverCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /*驾驶证类型*/
    private String cardtype;
    /*过期时间*/
    private Date expiredate;
}
