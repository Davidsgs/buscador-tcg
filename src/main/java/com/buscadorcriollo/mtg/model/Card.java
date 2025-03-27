package com.buscadorcriollo.mtg.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Card {
    private String name;
    private String condition;
    private String edition;
    private String price;
    private String urlImage;
}
