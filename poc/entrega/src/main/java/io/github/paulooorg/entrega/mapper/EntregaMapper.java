package io.github.paulooorg.entrega.mapper;

import io.github.paulooorg.entrega.controller.dto.response.EntregaDTO;
import io.github.paulooorg.entrega.entity.Entrega;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntregaMapper {
    EntregaMapper INSTANCE = Mappers.getMapper(EntregaMapper.class);

    EntregaDTO converteParaEntregaDTO(Entrega entrega);
}
