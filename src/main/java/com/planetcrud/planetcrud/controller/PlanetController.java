package com.planetcrud.planetcrud.controller;

import com.planetcrud.planetcrud.dto.PlanetDTO;
import com.planetcrud.planetcrud.model.Planet;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/planets", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlanetController {

    @GetMapping("/{id}")
    public ResponseEntity<Planet> getPlanet(@PathVariable("id") Long id) {
        System.out.println("GET PLANET");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Planet> createPlanet(@RequestBody PlanetDTO planetDTO) {
        System.out.println("CREATE PLANET");
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Planet>> getFilteredByName(@RequestParam String name) {
        System.out.println("GET FILTERED PLANETS");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/satellites")
    public ResponseEntity<List<Planet>> getSortedBySatellites() {
        System.out.println("GET SORTED PLANETS");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<List<Planet>> updatePlanet(@RequestBody PlanetDTO planetDTO) {
        System.out.println("UPDATE PLANET");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlanet(@PathVariable("id") Long id) {
        System.out.println("DELETE PLANET");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }





}


//1. Create planet
//        2. View one planet data along with satellites data
//        3. List of all planets with paging and filter by planet name
//        4. List of all planets with paging and sorted by number of satellites
//        5. Update planet data
//        6. Delete planet
//        7. Create satellite
//        8. List of all satellites of one planet
//        9. View of one satellite
//        10. Update satellite data
//        11. Delete satellite
