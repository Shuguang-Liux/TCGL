package com.example.demo.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class User {
    private Integer id;

    private String userName;

    private Integer userAge;

    private String userPassword;

    private Integer userRole;

    private String deleteState;

}