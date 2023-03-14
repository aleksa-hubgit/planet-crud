package com.planetcrud.planetcrud.integration;

import com.planetcrud.planetcrud.dto.PlanetDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        locations = {"classpath:test.properties"}
)
public class CreatePlanetTest {
    @Autowired
    private TestRestTemplate restTemplate;



    @Test
    public void testCreatePlanet() {
        PlanetDTO planetDTO = new PlanetDTO();
        planetDTO.setName("Earth");
        planetDTO.setMass(400L);
        planetDTO.setSurfaceArea(400L);
        planetDTO.setDistanceFromSun(500L);
        planetDTO.setAverageSurfaceTemperature(30);
        HttpEntity<PlanetDTO> entity = new HttpEntity<>(planetDTO);
        ResponseEntity<PlanetDTO> response = restTemplate.postForEntity("/planets", entity, PlanetDTO.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testCreatePlanetInvalidData() {
        PlanetDTO planetDTO = new PlanetDTO();
        planetDTO.setName("Earth");
        planetDTO.setMass(-30L);
        planetDTO.setSurfaceArea(400L);
        planetDTO.setDistanceFromSun(500L);
        planetDTO.setAverageSurfaceTemperature(30);
        HttpEntity<PlanetDTO> entity = new HttpEntity<>(planetDTO);
        ResponseEntity<String> response = restTemplate.postForEntity("/planets", entity, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.EXPECTATION_FAILED);
    }


}
