package com.buscadorcriollo.mtg.service.impl.stock;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardScraperInput {
    private String searchUrl;
    private Integer maxResults;
    private String cardName;
}
