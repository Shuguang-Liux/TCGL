package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class Record {
    private Integer sourceId;

    private String licensePlate;

    private String ownerName;

    private Date enterTime;

    private Date outTime;

    private BigDecimal billingPrice;

    private Integer accessTimes;

}