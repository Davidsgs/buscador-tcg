package com.buscadorcriollo.mtg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopDTO {
    private UUID id;
    private String name;
    private String shortName;
    private String url;
}
