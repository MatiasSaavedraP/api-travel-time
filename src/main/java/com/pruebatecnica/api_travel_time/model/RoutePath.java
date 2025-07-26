package com.pruebatecnica.api_travel_time.model;

import java.util.List;

public class RoutePath {

    private List<String> path;
    private double totalTime;

    public RoutePath(List<String> path, double totalTime) {
        this.path = path;
        this.totalTime = totalTime;
    }

    public List<String> getPath() {
        return path;
    }

    public double getTotalTime() {
        return totalTime;
    }
}
