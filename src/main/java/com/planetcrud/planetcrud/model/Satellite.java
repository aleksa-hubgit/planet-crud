package com.planetcrud.planetcrud.model;


import com.planetcrud.planetcrud.dto.SatelliteDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Satellite {
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

    @Column
    private Boolean naturalSatellite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planet_id", nullable = false)
    private Planet planet;

    public Satellite(SatelliteDTO satelliteDTO, Planet planet) {
        this.planet = planet;
        this.name = satelliteDTO.getName();;
        this.surfaceArea = satelliteDTO.getSurfaceArea();
        this.mass = satelliteDTO.getMass();
        this.naturalSatellite = satelliteDTO.getNaturalSatellite();
    }

}
