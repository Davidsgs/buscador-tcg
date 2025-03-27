package com.buscadorcriollo.mtg.enums;

import lombok.Getter;

import java.util.List;

@Getter
public enum TcgShop {

    PIRULO,
    MAGIC_LAIR,
    MAD_TOYZ,
    BATIKUEVA,
    DEALERS;

    public static List<TcgShop> getAll() {
        return List.of(TcgShop.values());
    }

}
