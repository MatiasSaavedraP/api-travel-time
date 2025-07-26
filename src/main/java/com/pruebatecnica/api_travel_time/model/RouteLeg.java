package com.pruebatecnica.api_travel_time.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "route_leg") 
public class RouteLeg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="loc_start")
    private String locStart;

    @Column(name="loc_end")
    private String locEnd;

    @Column(name="travel_time")
    private double travelTime;

    public RouteLeg(String locStart, String locEnd, double travelTime){
        this.locStart=locStart;
        this.locEnd=locEnd;
        this.travelTime=travelTime;
    }

    public RouteLeg(){

    }


}
