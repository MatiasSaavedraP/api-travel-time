package com.pruebatecnica.api_travel_time.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Arrays;

import com.pruebatecnica.api_travel_time.model.RouteLeg;
import com.pruebatecnica.api_travel_time.model.RoutePath;
import com.pruebatecnica.api_travel_time.repository.RouteLegRepository;

@ExtendWith(MockitoExtension.class)
public class RoutingServiceTest {

    @Mock
    private RouteLegRepository routeLegRepository;

    @InjectMocks
    private RoutingService routingService;

    @BeforeEach
    void setUp() {
        List<RouteLeg> legs = List.of(
            new RouteLeg("CP1", "CP2", 7),
            new RouteLeg("CP2", "R20", 67)
        );
        when(routeLegRepository.findAll()).thenReturn(legs);
        routingService.init();
    }

    @Test
    void testShortestPath() {
        RoutePath path = routingService.findShortestPath("CP1", "R20");

        List<String> expectedPath = Arrays.asList("CP1", "CP2", "R20");

        assertEquals(expectedPath, path.getPath());
        assertEquals(74.0, path.getTotalTime());
    }
    
}
