package com.planetcrud.planetcrud.util;

import com.planetcrud.planetcrud.dto.PlanetDTO;
import com.planetcrud.planetcrud.dto.SatelliteDTO;

public class Validator {
    public boolean isValidPlanet(PlanetDTO planetDTO) {
        if (planetDTO.getName() == null) return false;
        if (planetDTO.getName().isBlank()) return false;
        if (planetDTO.getSurfaceArea() == null) return false;
        if (planetDTO.getSurfaceArea() <= 0) return false;
        if (planetDTO.getMass() == null) return false;
        if (planetDTO.getMass() <= 0) return false;
        if (planetDTO.getDistanceFromSun() == null) return false;
        if (planetDTO.getDistanceFromSun() <= 0) return false;
        if (planetDTO.getAverageSurfaceTemperature() != null && planetDTO.getAverageSurfaceTemperature() <= 0) return false;
        if (planetDTO.getSatellites() != null)
            for (SatelliteDTO satellite: planetDTO.getSatellites()) {
                if (!isValidSatellite(satellite)) return false;
            }
        return true;
    }
    public boolean isValidSatellite(SatelliteDTO satelliteDTO) {
        if (satelliteDTO.getName() == null) return false;
        if(satelliteDTO.getName().isBlank()) return false;
        if(satelliteDTO.getSurfaceArea() == null) return false;
        if(satelliteDTO.getSurfaceArea() <= 0) return false;
        if(satelliteDTO.getMass() == null) return false;
        if(satelliteDTO.getMass() <= 0) return false;
        return true;
    }
}
