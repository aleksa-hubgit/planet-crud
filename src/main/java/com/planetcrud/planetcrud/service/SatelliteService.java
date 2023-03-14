package com.planetcrud.planetcrud.service;

import com.planetcrud.planetcrud.model.Satellite;
import com.planetcrud.planetcrud.repositories.SatelliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SatelliteService {

    private SatelliteRepository satelliteRepository;

    @Autowired
    public void setSatelliteRepository(SatelliteRepository satelliteRepository) {
        this.satelliteRepository = satelliteRepository;
    }


    public Satellite getSatelliteById(Long id) {
        Optional<Satellite> satelliteOptional = satelliteRepository.findById(id);
        return satelliteOptional.orElse(null);
    }

    public Satellite saveSatellite(Satellite satellite) {
        return satelliteRepository.save(satellite);
    }

    public List<Satellite> getSatellitesByPlanetId(Long planetId) {
        return satelliteRepository.findAllByPlanetId(planetId);
    }

    public boolean updateSatellite(Long id, Satellite satellite) {
        if (!satelliteRepository.existsById(id)) return false;
        satellite.setId(id);
        satelliteRepository.save(satellite);
        return true;
    }

    public boolean deleteSatellite(Long id) {
        if(!satelliteRepository.existsById(id)) return false;
        satelliteRepository.deleteById(id);
        return true;
    }
}
