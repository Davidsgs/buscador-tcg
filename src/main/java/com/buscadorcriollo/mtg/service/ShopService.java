package com.buscadorcriollo.mtg.service;

import com.buscadorcriollo.mtg.dto.ShopDTO;
import com.buscadorcriollo.mtg.enums.TcgType;

import java.util.List;

public interface ShopService {

    List<ShopDTO> getShops(TcgType tcgType);

}
