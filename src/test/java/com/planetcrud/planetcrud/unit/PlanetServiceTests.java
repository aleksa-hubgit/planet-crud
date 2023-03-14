package com.planetcrud.planetcrud.unit;


import com.planetcrud.planetcrud.model.Planet;
import com.planetcrud.planetcrud.repositories.PlanetRepository;
import com.planetcrud.planetcrud.service.PlanetService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class PlanetServiceTests {
    @Mock
    PlanetRepository planetRepository;

    @InjectMocks
    PlanetService planetService;

    @Test
    public void testGetPlanetByInvalidId() {
        when(planetRepository.findById(1L)).thenReturn(Optional.empty());
        assertNull(planetService.getPlanetById(1L));
    }

    @Test
    public void testSavePlanet() {
        Planet planet = new Planet();
        planet.setName("Earth");
        planet.setMass(500L);
        planet.setDistanceFromSun(40L);
        planet.setSurfaceArea(400L);

        when(planetRepository.save(planet)).thenReturn(planet);
        assertEquals(planetService.savePlanet(planet).getName(), planet.getName());
    }

    @Test
    public void testDeletePlanetInvalidId() {
        when(planetRepository.existsById(1L)).thenReturn(false);
        assertFalse(planetService.deletePlanet(1L));
    }

    @Test
    public void testDeletePlanetById() {
        when(planetRepository.existsById(1L)).thenReturn(true);
        assertTrue(planetService.deletePlanet(1L));
    }

}
