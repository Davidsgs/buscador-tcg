package com.buscadorcriollo.mtg.service.impl;

import com.buscadorcriollo.mtg.dto.CodeDescription;
import com.buscadorcriollo.mtg.enums.TcgType;
import com.buscadorcriollo.mtg.service.TcgService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TcgServiceImpl implements TcgService {

    @Override
    public List<CodeDescription> listTcgTypes() {
        return TcgType
                .getAll()
                .stream()
                .map(
                        type
                                ->
                                CodeDescription
                                        .builder()
                                        .code(type.name())
                                        .description(type.getDescription())
                                        .build()
                )
                .toList();
    }
}
