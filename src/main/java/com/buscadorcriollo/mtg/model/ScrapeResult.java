package com.buscadorcriollo.mtg.model;

import lombok.Data;

@Data
public class ScrapeResult<T> {

    public ScrapeResult(T data, Integer statusCode) {
        this.data = data;
        this.statusCode = statusCode;
    }

    private Integer statusCode;
    private T data;

}
