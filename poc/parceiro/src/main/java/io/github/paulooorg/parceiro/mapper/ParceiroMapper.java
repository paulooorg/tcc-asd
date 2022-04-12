package io.github.paulooorg.parceiro.mapper;

import io.github.paulooorg.parceiro.controller.dto.request.FormParceiroDTO;
import io.github.paulooorg.parceiro.entity.Parceiro;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParceiroMapper {
    ParceiroMapper INSTANCE = Mappers.getMapper(ParceiroMapper.class);

    Parceiro converteParaParceiro(FormParceiroDTO formParceiro);
}
