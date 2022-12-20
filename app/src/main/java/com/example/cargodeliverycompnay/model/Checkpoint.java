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
@AllArgsConstructor
public class Checkpoint {
    private int id;

    private String address;

    private LocalDate checkpointDate;

    private List<Destination> destinations;

    public Checkpoint(String address, LocalDate checkpointDate) {
        this.address = address;
        this.checkpointDate = checkpointDate;
    }

    @Override
    public String toString() {
        return "Address= " + address +
                ", Date=" + checkpointDate;
    }
}
