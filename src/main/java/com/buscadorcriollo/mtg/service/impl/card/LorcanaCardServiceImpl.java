package com.buscadorcriollo.mtg.service.impl.card;

import com.buscadorcriollo.mtg.cache.CardCache;
import com.buscadorcriollo.mtg.enums.TcgType;
import com.buscadorcriollo.mtg.service.CardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LorcanaCardServiceImpl implements CardService {

    @Override
    public List<String> getCardNames() {
        return CardCache.getInstance().getCardNames(TcgType.MAGIC);
    }

    @Override
    public TcgType getType() {
        return null;
    }

    @Override
    public List<String> loadCardNames() {
        return List.of();
    }

}
