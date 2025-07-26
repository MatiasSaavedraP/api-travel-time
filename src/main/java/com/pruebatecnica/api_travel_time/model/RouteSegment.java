package com.pruebatecnica.api_travel_time.model;

public class RouteSegment {
    
    private String destination;
    private double cost;

    public RouteSegment(String destination, double cost) {
        this.destination = destination;
        this.cost = cost;
    }

    public String getDestination() {
        return destination;
    }

    public double getCost() {
        return cost;
    }

}
