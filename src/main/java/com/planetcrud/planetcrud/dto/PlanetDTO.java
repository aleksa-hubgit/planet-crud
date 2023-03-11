package com.planetcrud.planetcrud.dto;

import com.planetcrud.planetcrud.model.Planet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanetDTO {
    private String name;

    private Long surfaceArea;

    private Long mass;

    private Long distanceFromSun;

    private Integer averageSurfaceTemperature;

    private List<SatelliteDTO> satellites;

    public PlanetDTO(Planet planet) {
        this.name = planet.getName();
        this.surfaceArea = planet.getSurfaceArea();
        this.mass = planet.getMass();
        this.distanceFromSun = planet.getDistanceFromSun();
        this.averageSurfaceTemperature = getAverageSurfaceTemperature();
        this.satellites = new ArrayList<>();
        planet.getSatellites().forEach(satellite -> satellites.add(new SatelliteDTO(satellite)));
    }


}
