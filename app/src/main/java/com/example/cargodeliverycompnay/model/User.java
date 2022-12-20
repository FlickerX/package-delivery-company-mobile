package com.example.cargodeliverycompnay.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class User implements Serializable {
    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private LocalDate birthday;
    private String phoneNo;
    private Double salary;


    public User(String login, String password, String name, String surname, LocalDate birthday, String phoneNo, Double salary) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.phoneNo = phoneNo;
        this.salary = salary;
    }

}
