package com.example.cargodeliverycompnay.api;

public class Constants {
    public static final String ADDRESS = "http://172.31.16.1:8080";
    public static final String MANAGER_LOGIN_URL = ADDRESS + "/validateManager";
    public static final String COURIER_LOGIN_URL = ADDRESS + "/validateCourier";
    public static final String ALL_TRUCKS_URL = ADDRESS + "/allTrucks";
    public static final String ALL_CARGOS_URL = ADDRESS + "/allCargos";
    public static final String ALL_MANGERS_URL = ADDRESS + "/allManagers";
    public static final String ALL_COURIERS_URL = ADDRESS + "/allCouriers";
    public static final String ALL_ORDERS_URL = ADDRESS + "/allDestinations";
    public static final String DESTINATION_BY_ID = ADDRESS + "/destination/";
    public static final String UPDATE_DESTINATION = ADDRESS + "/updateDestination";
    public static final String DELETE_TRUCK_BY_ID = ADDRESS + "/deleteTruck/";
    public static final String TRUCK_BY_ID = ADDRESS + "/truck/";
}
