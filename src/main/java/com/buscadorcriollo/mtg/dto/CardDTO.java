package com.buscadorcriollo.mtg.dto;

import lombok.Data;

@Data
public class CardDTO {
    private String name;
    private String condition;
    private String edition;
    private String price;
    private String imageUrl;
}
