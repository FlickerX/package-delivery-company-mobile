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
public class Truck {
    private int id;

    private String mark;
    private String model;
    private Double engineLiters;
    private Integer horsePower;
    private String color;
    private Boolean isAvailable;

    private List<Destination> destinations;

    public Truck(String mark, String model, Double engineLiters, Integer horsePower, String color) {
        this.mark = mark;
        this.model = model;
        this.engineLiters = engineLiters;
        this.horsePower = horsePower;
        this.color = color;
    }

    public Truck(int id, String mark, String model, Double engineLiters, Integer horsePower, String color) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.engineLiters = engineLiters;
        this.horsePower = horsePower;
        this.color = color;
    }

    @Override
    public String toString() {
        return mark + " " +
                model +
                ", Engine liters=" + engineLiters +
                ", Color='" + color;
    }

    public Integer getId() {
        return this.id;
    }
}
