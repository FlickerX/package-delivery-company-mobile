package com.example.cargodeliverycompnay.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cargo {
    private int id;

    private String naming;

    private double weight;

    private List<Destination> destinations;

    public Cargo(String naming, double weight) {
        this.naming = naming;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Title = " + naming +
                ", Weight = " + weight;
    }

    public int getId() {
        return id;
    }
}
