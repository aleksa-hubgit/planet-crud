package com.planetcrud.planetcrud.controller;

import com.planetcrud.planetcrud.dto.PlanetDTO;
import com.planetcrud.planetcrud.dto.SatelliteDTO;
import com.planetcrud.planetcrud.exceptions.InvalidPlanetDataException;
import com.planetcrud.planetcrud.exceptions.InvalidSatelliteDataException;
import com.planetcrud.planetcrud.exceptions.PlanetNotFoundException;
import com.planetcrud.planetcrud.exceptions.SatelliteNotFoundException;
import com.planetcrud.planetcrud.model.Planet;
import com.planetcrud.planetcrud.model.Satellite;
import com.planetcrud.planetcrud.repositories.SatelliteRepository;
import com.planetcrud.planetcrud.service.PlanetService;
import com.planetcrud.planetcrud.service.SatelliteService;
import com.planetcrud.planetcrud.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/satellites", produces = MediaType.APPLICATION_JSON_VALUE)
public class SatelliteController {

    private SatelliteService satelliteService;

    private PlanetService planetService;

    @Autowired
    public void setSatelliteService(SatelliteService satelliteService) {
        this.satelliteService = satelliteService;
    }

    @Autowired
    public void setPlanetService(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SatelliteDTO> getSatellite(@PathVariable("id") Long id) {
        System.out.println("GET SATELLITE");
        Satellite satellite = satelliteService.getSatelliteById(id);
        if (satellite == null) throw new SatelliteNotFoundException(id);
        return new ResponseEntity<>(new SatelliteDTO(satellite), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<SatelliteDTO> createSatellite(@RequestBody SatelliteDTO satelliteDTO, @RequestParam(name = "planet_id") Long planetId) {
        System.out.println("CREATE SATELLITE");
        Planet planet = planetService.getPlanetById(planetId);
        if (planet == null) throw new PlanetNotFoundException(planetId);
        Validator validator = new Validator();
        if (!validator.isValidSatellite(satelliteDTO)) throw new InvalidSatelliteDataException();
        Satellite satellite = satelliteService.saveSatellite(new Satellite(satelliteDTO, planet));
        return new ResponseEntity<>(new SatelliteDTO(satellite), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<SatelliteDTO>> getSatellites(@RequestParam(name = "planet_id") Long planetId) {
        System.out.println("GET SATELLITES");
        return new ResponseEntity<>(convertToDTOS(satelliteService.getSatellitesByPlanetId(planetId)), HttpStatus.OK);
    }


    // ovo bi iskreno pitao koja je praksa kada mora da se prosledi i id roditelja jer kao ne znam cudna su mi resenja
    @PutMapping("")
    public ResponseEntity<SatelliteDTO> updateSatellite(@RequestBody SatelliteDTO satelliteDTO, @RequestParam(name = "id") Long id, @RequestParam(name = "planet_id") Long planetId) {
        System.out.println("UPDATE SATELLITE");
        Planet planet = planetService.getPlanetById(planetId);
        if (planet == null) throw new PlanetNotFoundException(planetId);
        Validator validator = new Validator();
        if (!validator.isValidSatellite(satelliteDTO)) throw new InvalidSatelliteDataException();
        if (!satelliteService.updateSatellite(id, new Satellite(satelliteDTO, planet))) throw new SatelliteNotFoundException(id);
        return new ResponseEntity<>(satelliteDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSatellite(@PathVariable("id") Long id) {
        System.out.println("DELETE SATELLITE");
        if(!satelliteService.deleteSatellite(id)) throw new SatelliteNotFoundException(id);
        return new ResponseEntity<>("Successfully deleted satellite with id: " + id, HttpStatus.OK);
    }

    @ExceptionHandler(SatelliteNotFoundException.class)
    public ResponseEntity<String> satelliteNotFound(SatelliteNotFoundException exception) {
        long id = exception.getSatelliteId();
        String errorMessage = "Satellite with id: " + id + " not found.";
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PlanetNotFoundException.class)
    public ResponseEntity<String> planetNotFound(PlanetNotFoundException exception) {
        long id = exception.getPlanetId();
        String errorMessage = "Planet with id: " + id + " not found.";
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(InvalidSatelliteDataException.class)
    public ResponseEntity<String> invalidSatelliteData(InvalidSatelliteDataException exception) {
        String errorMessage = "Invalid data for satellite.";
        return new ResponseEntity<>(errorMessage, HttpStatus.EXPECTATION_FAILED);
    }

    List<SatelliteDTO> convertToDTOS(List<Satellite> satellites) {
        List<SatelliteDTO> satelliteDTOS = new ArrayList<>();
        for (Satellite satellite: satellites) {
            satelliteDTOS.add(new SatelliteDTO(satellite));
        }
        return satelliteDTOS;
    }
}
