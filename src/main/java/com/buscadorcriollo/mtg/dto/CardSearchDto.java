package com.buscadorcriollo.mtg.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CardSearchDto {
    private Integer values;
    private List<String> cardsNames;
}
