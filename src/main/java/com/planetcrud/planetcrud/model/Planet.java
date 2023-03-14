package com.planetcrud.planetcrud.model;

import com.planetcrud.planetcrud.dto.PlanetDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long surfaceArea;

    @Column(nullable = false)
    private Long mass;

    @Column(nullable = false)
    private Long distanceFromSun;

    @Column
    private Integer averageSurfaceTemperature;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "planet", cascade = CascadeType.ALL)
    private List<Satellite> satellites;

    @Formula("(SELECT COUNT(*) FROM satellite s WHERE s.planet_id = id)")
    private int satelliteCount;

    public Planet(PlanetDTO planetDTO) {
        this.name = planetDTO.getName();
        this.surfaceArea = planetDTO.getSurfaceArea();;
        this.mass = planetDTO.getMass();
        this.distanceFromSun = planetDTO.getDistanceFromSun();
        this.averageSurfaceTemperature = planetDTO.getAverageSurfaceTemperature();
        this.satellites = new ArrayList<>();
        if (planetDTO.getSatellites() != null)
            planetDTO.getSatellites().forEach(satelliteDTO -> satellites.add(new Satellite(satelliteDTO, this)));
    }


}
