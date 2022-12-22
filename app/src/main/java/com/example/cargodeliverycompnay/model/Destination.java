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
    private Truck truck;

    public Destination(String address, LocalDate requestedDeliveryDate, LocalDate deliveryStartDate, OrderStatus status) {
        this.address = address;
        this.requestedDeliveryDate = requestedDeliveryDate;
        this.deliveryStartDate = deliveryStartDate;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Address = " + address +
                ", Requested delivery date= " + requestedDeliveryDate +
                ", " + status;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getRequestedDeliveryDate() {
        return requestedDeliveryDate;
    }

    public LocalDate getDeliveryStartDate() {
        return deliveryStartDate;
    }

    public LocalDate getDeliveryEndDate() {
        return deliveryEndDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Truck getTruck() {
        return truck;
    }
}
