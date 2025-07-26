package com.pruebatecnica.api_travel_time.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.pruebatecnica.api_travel_time.model.RouteLeg;
import com.pruebatecnica.api_travel_time.model.RoutePath;
import com.pruebatecnica.api_travel_time.model.RouteSegment;
import com.pruebatecnica.api_travel_time.repository.RouteLegRepository;

import jakarta.annotation.PostConstruct;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Comparator;

@Service
public class RoutingService {

    @Autowired
    private RouteLegRepository repository;

    private Map<String, List<RouteSegment>> graph = new HashMap<>();

    @PostConstruct
    public void init() {
        List<RouteLeg> legs = repository.findAll();
        for (RouteLeg leg : legs) {
            graph.computeIfAbsent(leg.getLocStart(), k -> new ArrayList<>())
                 .add(new RouteSegment(leg.getLocEnd(), leg.getTravelTime()));
        }
    }

    public RoutePath findShortestPath(String start, String end) {
        Map<String, Double> distance = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingDouble(distance::get));

        for (String node : graph.keySet()) {
            distance.put(node, Double.POSITIVE_INFINITY);
        }

        distance.put(start, 0.0);
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(end)) break;

            for (RouteSegment neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                double newDist = distance.get(current) + neighbor.getCost();
                if (newDist < distance.getOrDefault(neighbor.getDestination(), Double.POSITIVE_INFINITY)) {
                    distance.put(neighbor.getDestination(), newDist);
                    previous.put(neighbor.getDestination(), current);
                    queue.add(neighbor.getDestination());
                }
            }
        }

        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }

        Collections.reverse(path);

        if (path.size() == 1 && !path.get(0).equals(start)) {
            return new RoutePath(List.of(), Double.POSITIVE_INFINITY);
        }

        return new RoutePath(path, distance.get(end));
    }
}
