package com.planetcrud.planetcrud.dto;


import com.planetcrud.planetcrud.model.Satellite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SatelliteDTO {
    private String name;

    private Long surfaceArea;

    private Long mass;

    private Boolean naturalSatellite;

    public SatelliteDTO(Satellite satellite) {
        this.name = satellite.getName();
        this.surfaceArea = satellite.getSurfaceArea();
        this.mass = satellite.getMass();
        this.naturalSatellite = satellite.getNaturalSatellite();
    }


}
