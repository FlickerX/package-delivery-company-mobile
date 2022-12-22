package com.example.cargodeliverycompnay.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Courier extends User{
    private String driverLicense;
    private String healthCertificate;

    private List<Destination> destinations;

    public Courier(String login, String password, String name, String surname, LocalDate birthday, String phoneNo, Double salary, String driverLicense, String healthCertificate) {
        super(login, password, name, surname, birthday, phoneNo, salary);
        this.driverLicense = driverLicense;
        this.healthCertificate = healthCertificate;
    }
}
