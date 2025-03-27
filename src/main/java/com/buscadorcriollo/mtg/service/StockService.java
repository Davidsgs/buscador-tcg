package com.buscadorcriollo.mtg.service;

import com.buscadorcriollo.mtg.dto.StockResultDTO;
import com.buscadorcriollo.mtg.enums.TcgType;

import java.util.UUID;

public interface StockService {

    StockResultDTO<?> getStocks(TcgType tcgType, UUID shopId, String cardName, Integer integer);

}
