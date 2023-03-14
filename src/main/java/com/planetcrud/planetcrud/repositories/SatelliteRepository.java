package com.planetcrud.planetcrud.repositories;

import com.planetcrud.planetcrud.model.Satellite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SatelliteRepository extends JpaRepository<Satellite, Long> {
    List<Satellite> findAllByPlanetId(Long planetId);
}
