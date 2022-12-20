package com.example.cargodeliverycompnay.model;

import com.example.cargodeliverycompnay.Enums.OrderStatus;

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
public class Destination {
    private int id;
    private String address;
    private LocalDate requestedDeliveryDate;
    private LocalDate deliveryStartDate;

    private LocalDate deliveryEndDate;
    private OrderStatus status;

    private List<Manager> managers;

    private List<Cargo> cargos;

    private List<Checkpoint> checkpoints;

    private Courier courier;

    private Truck truck;

    private List<Forum> forums;
    public Destination(String address, LocalDate requestedDeliveryDate, LocalDate deliveryStartDate, OrderStatus status, List<Manager> managers, List<Cargo> cargos, Courier courier, Truck truck) {
        this.address = address;
        this.requestedDeliveryDate = requestedDeliveryDate;
        this.deliveryStartDate = deliveryStartDate;
        this.status = status;
        this.managers = managers;
        this.cargos = cargos;
        this.courier = courier;
        this.truck = truck;
    }

    public Destination(String address, LocalDate requestedDeliveryDate, LocalDate deliveryStartDate, OrderStatus status, List<Manager> managers, List<Cargo> cargos, Truck truck) {
        this.address = address;
        this.requestedDeliveryDate = requestedDeliveryDate;
        this.deliveryStartDate = deliveryStartDate;
        this.status = status;
        this.managers = managers;
        this.cargos = cargos;
        this.truck = truck;
    }

    @Override
    public String toString() {
        return "Address = " + address +
                ", requestedDeliveryDate=" + requestedDeliveryDate +
                " " + status +
                ", courier=" + courier +
                ", truck=" + truck;
    }
}
