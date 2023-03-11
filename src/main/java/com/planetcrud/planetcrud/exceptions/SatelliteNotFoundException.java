package com.planetcrud.planetcrud.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SatelliteNotFoundException extends RuntimeException{
    private Long satelliteId;

}
