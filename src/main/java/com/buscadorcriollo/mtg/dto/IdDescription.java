package com.buscadorcriollo.mtg.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdDescription {
    private int id;
    private String description;
}
