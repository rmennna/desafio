package com.fourd.desafio.mapper;

import com.fourd.desafio.domain.Aula;
import com.fourd.desafio.requests.AulaPostRequestBody;
import com.fourd.desafio.requests.AulaPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AulaMapper {

    public static final AulaMapper INSTANCE = Mappers.getMapper(AulaMapper.class);

    public abstract Aula toAula(AulaPostRequestBody aulaPostRequestBody);
    public abstract Aula toAula(AulaPutRequestBody aulaPutRequestBody);
}
