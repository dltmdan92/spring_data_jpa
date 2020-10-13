package com.seungmoo.sprintboot.spring_data_jpa.account;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue // value값을 자동으로 생성
    private Long id;

    private String username;

    private String password;

    private String email;

    private Boolean active;

}
