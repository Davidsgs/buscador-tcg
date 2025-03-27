package com.buscadorcriollo.mtg.dto;

import lombok.Data;

import java.util.List;

@Data
public class StockResultDTO<T> {

    public StockResultDTO(String searchUrl, List<T> items, Integer resultCode) {
        this.searchUrl = searchUrl;
        this.items = items;
        this.results = items.size();
        this.resultCode = resultCode;

    }

    private String searchUrl;
    private List<T> items;
    private Integer results;
    private Integer resultCode;

}
