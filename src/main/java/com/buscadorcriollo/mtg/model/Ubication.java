package com.buscadorcriollo.mtg.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ubication {
    private Double geoX;
    private Double geoY;
}
