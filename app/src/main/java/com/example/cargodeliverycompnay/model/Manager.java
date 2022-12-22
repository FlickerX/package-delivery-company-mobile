package com.example.cargodeliverycompnay.model;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Manager extends User{
    private boolean isAdmin;


    public Manager(String login, String password, String name, String surname, LocalDate birthday, String phoneNo, Double salary, boolean isAdmin) {
        super(login, password, name, surname, birthday, phoneNo, salary);
        this.isAdmin = isAdmin;
    }
}
