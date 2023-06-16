package com.fourd.desafio.mapper;

import com.fourd.desafio.domain.Aula;
import com.fourd.desafio.domain.Professor;
import com.fourd.desafio.requests.AulaPostRequestBody;
import com.fourd.desafio.requests.AulaPutRequestBody;
import com.fourd.desafio.requests.ProfessorPostRequestBody;
import com.fourd.desafio.requests.ProfessorPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ProfessorMapper {

    public static final ProfessorMapper INSTANCE = Mappers.getMapper(ProfessorMapper.class);

    public abstract Professor toProfessor(ProfessorPostRequestBody professorPostRequestBody);
    public abstract Professor toProfessor(ProfessorPutRequestBody professorPutRequestBody);
}
