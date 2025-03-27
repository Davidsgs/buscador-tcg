package com.buscadorcriollo.mtg.service.impl.card;

import com.buscadorcriollo.mtg.enums.TcgType;
import com.buscadorcriollo.mtg.service.CardService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CardServiceFactory {

    private final CardService magicCardService;

    public CardServiceFactory(
            @Qualifier("magicCardServiceImpl") CardService magicCardService) {
        this.magicCardService = magicCardService;
    }

    public CardService getService(TcgType tcgType) throws ClassNotFoundException {
        return switch (tcgType) {
            case MAGIC -> magicCardService;
            case LORCANA, ONE_PIECE -> throw new ClassNotFoundException("Impossible to handle this tcg type: " + tcgType);
        };
    }
}