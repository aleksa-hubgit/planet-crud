package com.planetcrud.planetcrud.model;

import jakarta.persistence.*;
import lombok.*;

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

    private Integer averageSurfaceTemperature;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "planet", cascade = CascadeType.ALL)
    private List<Satellite> satellites;



}
