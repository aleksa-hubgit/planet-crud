package com.planetcrud.planetcrud.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanetNotFoundException extends RuntimeException {
    private Long planetId;

}
