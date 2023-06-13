package com.fourd.desafio.mapper;

import com.fourd.desafio.domain.Aula;
import com.fourd.desafio.domain.Usuario;
import com.fourd.desafio.requests.AulaPostRequestBody;
import com.fourd.desafio.requests.AulaPutRequestBody;
import com.fourd.desafio.requests.UsuarioPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UsuarioMapper {

    public static final UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    public abstract Usuario toUsuario(UsuarioPostRequestBody usuarioPostRequestBody);

//    public abstract Usuario toAula(AulaPutRequestBody aulaPutRequestBody);
}
