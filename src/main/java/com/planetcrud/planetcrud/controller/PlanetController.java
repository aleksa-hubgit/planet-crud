package com.planetcrud.planetcrud.controller;

import com.planetcrud.planetcrud.service.PlanetService;
import com.planetcrud.planetcrud.dto.PlanetDTO;
import com.planetcrud.planetcrud.dto.SatelliteDTO;
import com.planetcrud.planetcrud.exceptions.InvalidPlanetDataException;
import com.planetcrud.planetcrud.exceptions.PlanetNotFoundException;
import com.planetcrud.planetcrud.model.Planet;
import com.planetcrud.planetcrud.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/planets", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlanetController {

    private PlanetService planetService;
    @Autowired
    public void setPlanetService(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanetDTO> getPlanet(@PathVariable("id") Long id) {
        System.out.println("GET PLANET");
        Planet planet = planetService.getPlanetById(id);
        if (planet == null) throw new PlanetNotFoundException(id);
        return new ResponseEntity<>(new PlanetDTO(planet), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<PlanetDTO> createPlanet(@RequestBody PlanetDTO planetDTO) {
        System.out.println("CREATE PLANET");
        Validator validator = new Validator();
        if (!validator.isValidPlanet(planetDTO)) throw new InvalidPlanetDataException();
        Planet planet = planetService.savePlanet(new Planet(planetDTO));
        return new ResponseEntity<>(new PlanetDTO(planet), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<PlanetDTO>> getFilteredByName(Pageable pageable) {
        System.out.println("GET FILTERED PLANETS");
        return new ResponseEntity<>(convertToDTOS(planetService.getPlanetsByPageable(pageable)), HttpStatus.OK);
    }

    @GetMapping("/satellites")
    public ResponseEntity<List<PlanetDTO>> getSortedBySatellites(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        System.out.println("GET SORTED PLANETS");
        Pageable pageable = PageRequest.of(page, size, Sort.by("satelliteCount").descending());
        return new ResponseEntity<>(convertToDTOS(planetService.getPlanetsByPageable(pageable)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanetDTO> updatePlanet(@PathVariable("id") Long id, @RequestBody PlanetDTO planetDTO) {
        System.out.println("UPDATE PLANET");
        Validator validator = new Validator();
        if (!validator.isValidPlanet(planetDTO)) throw new InvalidPlanetDataException();
        if (!planetService.updatePlanet(id, new Planet(planetDTO))) throw new PlanetNotFoundException(id);
        return new ResponseEntity<>(planetDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlanet(@PathVariable("id") Long id) {
        System.out.println("DELETE PLANET");
        if(!planetService.deletePlanet(id)) throw new PlanetNotFoundException(id);
        return new ResponseEntity<>("Successfully deleted planet with id: " + id, HttpStatus.OK);
    }

    @ExceptionHandler(PlanetNotFoundException.class)
    public ResponseEntity<String> planetNotFound(PlanetNotFoundException exception) {
        long id = exception.getPlanetId();
        String errorMessage = "Planet with id: " + id + " not found.";
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPlanetDataException.class)
    public ResponseEntity<String> invalidPlanetData(InvalidPlanetDataException exception) {
        String errorMessage = "Invalid data for planet.";
        return new ResponseEntity<>(errorMessage, HttpStatus.EXPECTATION_FAILED);
    }

    List<PlanetDTO> convertToDTOS(List<Planet> planets) {
        List<PlanetDTO> planetDTOS = new ArrayList<>();
        for (Planet planet: planets) {
            planetDTOS.add(new PlanetDTO(planet));
        }
        return planetDTOS;
    }


}

//    Create Java backend application for planet CRUD management.
//
//        Technical requirements:
//        - Spring Boot v2+ project with Maven v3+ build
//        - Hibernate v5+ JPA
//        - PostgreSQL v9+ DBMS
//        - H2 for e2e integration tests
//        - IDE optional
//        - Git public code repository: BitBucket, GitHub, GitLab...
//
//        Planet object fields:
//        - name (String) (mandatory)
//        - surfaceArea (Long) (mandatory)
//        - mass (Long) (mandatory)
//        - distanceFromSun (Long) (mandatory)
//        - averageSurfaceTemperature (Integer) (optional)
//        - satellites (List<Satellite>)
//
//        Satellite object fields:
//        - name (String) (mandatory)
//        - surfaceArea (Long) (mandatory)
//        - mass (Long) (mandatory)
//        - naturalSatelitte (Boolean) (optional)
//
//        Create REST API's:
//        1. Create planet
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
//
//        Create at least 3 JUnit and 1 e2e integration test.
//        Try to use some of the best programming practices.
//
//        Bonus feature:
//        Create Postman (JSON) collection for REST API testing.

