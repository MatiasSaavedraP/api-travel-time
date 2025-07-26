package com.pruebatecnica.api_travel_time.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pruebatecnica.api_travel_time.model.RouteLeg;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteLegRepository extends JpaRepository<RouteLeg, Long>{
    
}
