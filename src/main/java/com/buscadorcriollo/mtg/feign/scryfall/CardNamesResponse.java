package com.buscadorcriollo.mtg.feign.scryfall;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CardNamesResponse {
    private String object;
    private String uri;
    @JsonProperty("total_values")
    private Long totalValues;
    private List<String> data;
}
