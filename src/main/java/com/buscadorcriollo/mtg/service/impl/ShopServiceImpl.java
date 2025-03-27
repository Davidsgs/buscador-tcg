package com.buscadorcriollo.mtg.service.impl;

import com.buscadorcriollo.mtg.cache.ShopCache;
import com.buscadorcriollo.mtg.dto.ShopDTO;
import com.buscadorcriollo.mtg.enums.TcgType;
import com.buscadorcriollo.mtg.service.ShopService;
import com.buscadorcriollo.mtg.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    private ShopServiceImpl() {}

    @Override
    public List<ShopDTO> getShops(TcgType tcgType) {
        var shops = ShopCache.getInstance().getAll(tcgType);
        return shops
                .stream()
                .map(
                        shop -> MapperUtil.map(shop, ShopDTO.class)
                )
                .toList();
    }
}
