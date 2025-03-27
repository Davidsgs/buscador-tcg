package com.buscadorcriollo.mtg.enums;

import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public enum TcgType {
    MAGIC(1, "Magic: The Gathering"),
    LORCANA(2, "Disney Lorcana"),
    ONE_PIECE(3, "One Piece TCG");

    TcgType(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    private final Integer id;
    private final String description;

    public static List<TcgType> getAll() {
        return List.of(TcgType.values());
    }

    public static TcgType getById(Integer id) {
        if(Objects.isNull(id)) return null;
        return TcgType
                .getAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Id not found"));
    }
}
