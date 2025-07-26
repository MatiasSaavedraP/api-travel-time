package com.pruebatecnica.api_travel_time.service;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.pruebatecnica.api_travel_time.model.RouteLeg;

import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Set;
// import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;

import com.pruebatecnica.api_travel_time.repository.RouteLegRepository;

@Service
public class RouteLegService {

    @Autowired
    private RouteLegRepository routeLegRepository;

    @Autowired
    private RoutingService routingService;

    public List<RouteLeg> getRougeLegs(){
        return routeLegRepository.findAll();
    }

    public void saveRouteLegs(List<RouteLeg> routeLegs){
        routeLegRepository.saveAll(routeLegs);
    }

    public void importRouteLegFromCsv(MultipartFile file) throws Exception {

        CSVParser parser = new CSVParserBuilder()
        .withSeparator(';')
        .build();

        try (
        CSVReader reader = new CSVReaderBuilder(new InputStreamReader(file.getInputStream()))
        .withCSVParser(parser)
        .build()) {
            List<String[]> rows = reader.readAll();
            List<RouteLeg> legs = new ArrayList<RouteLeg>();
            for (String[] line : rows) {
                // System.out.println(Arrays.toString(line));
                String x = line[0];
                // System.out.println(line[0]);
                String y = line[1];
                double travelTime = Double.parseDouble(line[2]);
                legs.add(new RouteLeg(x, y, travelTime));
            }

            /// Comparacion de legs existentes:

            List<Object[]> existentData = routeLegRepository.findAllLocStartAndLocEnd();
            Set<String> existingPairs = existentData.stream()
                        .map(arr -> arr[0] + "|" + arr[1])
                        .collect(Collectors.toSet());

            List<RouteLeg> newLegs = legs.stream()
                        .filter(leg -> !existingPairs.contains(leg.getLocStart() + "|" + leg.getLocEnd()))
                        .collect(Collectors.toList());

            saveRouteLegs(newLegs);
            routingService.init();
        }
    }

    
}
