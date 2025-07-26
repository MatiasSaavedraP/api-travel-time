package com.pruebatecnica.api_travel_time.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pruebatecnica.api_travel_time.model.RouteLeg;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RouteLegRepository extends JpaRepository<RouteLeg, Long>{

    @Query("SELECT r.locStart, r.locEnd FROM RouteLeg r")
    List<Object[]> findAllLocStartAndLocEnd();
    
}
