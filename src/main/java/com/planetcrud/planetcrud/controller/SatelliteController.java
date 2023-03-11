package com.planetcrud.planetcrud.controller;

import com.planetcrud.planetcrud.dto.SatelliteDTO;
import com.planetcrud.planetcrud.exceptions.SatelliteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/satellites", produces = MediaType.APPLICATION_JSON_VALUE)
public class SatelliteController {

    @GetMapping("/{id}")
    public ResponseEntity<String> getSatellite(@PathVariable("id") Long id) {
        System.out.println("GET SATELLITE");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> createSatellite(@RequestBody SatelliteDTO satelliteDTO) {
        System.out.println("CREATE SATELLITE");
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/planet/{id}")
    public ResponseEntity<List<String>> getSatellites(@PathVariable("id") Long planetId) {
        System.out.println("GET SATELLITES");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    @PutMapping("")
    public ResponseEntity<List<String>> updateSatellite(@RequestBody SatelliteDTO satelliteDTO) {
        System.out.println("UPDATE SATELLITE");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSatellite(@PathVariable("id") Long id) {
        System.out.println("DELETE SATELLITE");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @ExceptionHandler(SatelliteNotFoundException.class)
    public ResponseEntity<String> satelliteNotFound(SatelliteNotFoundException exception) {
        long id = exception.getSatelliteId();
        String errorMessage = "Satellite with id: " + id + "not found.";
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
