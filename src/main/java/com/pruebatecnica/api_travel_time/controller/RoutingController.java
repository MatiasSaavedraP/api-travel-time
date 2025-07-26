package com.pruebatecnica.api_travel_time.controller;

import org.springframework.web.bind.annotation.*;
import com.pruebatecnica.api_travel_time.model.RoutePath;
import com.pruebatecnica.api_travel_time.service.RoutingService;

@RestController
@RequestMapping("/api/routes")
public class RoutingController {

    private final RoutingService service;

    public RoutingController(RoutingService service) {
        this.service = service;
    }

    @GetMapping("/best-path")
    public RoutePath getShortestPath(@RequestParam String from, @RequestParam String to) {
        return service.findShortestPath(from, to);
    }
}
