package com.planetcrud.planetcrud.service;
import com.planetcrud.planetcrud.model.Planet;
import com.planetcrud.planetcrud.repositories.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetService {
    private PlanetRepository planetRepository;

    @Autowired
    public void setPlanetRepository(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public Planet getPlanetById(Long id) {
        Optional<Planet> planet = planetRepository.findById(id);
        return planet.orElse(null);
    }

    public Planet savePlanet(Planet planet) {
        planet = planetRepository.save(planet);
        return planet;
    }

    public boolean deletePlanet(Long id) {
        if(!planetRepository.existsById(id)) return false;
        planetRepository.deleteById(id);
        return true;
    }

    public List<Planet> getPlanetsByPageable(Pageable pageable) {
        return planetRepository.findAll(pageable).getContent();
    }

    public boolean updatePlanet(Long id, Planet planet) {
        if (!planetRepository.existsById(id)) return false;
        planet.setId(id);
        planetRepository.save(planet);
        return true;
    }

}
