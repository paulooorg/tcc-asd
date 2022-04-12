package io.github.paulooorg.cliente.mapper;

import io.github.paulooorg.cliente.controller.dto.request.FormVolumeDTO;
import io.github.paulooorg.cliente.producer.dto.MessageVolumeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VolumeMapper {
    VolumeMapper INSTANCE = Mappers.getMapper(VolumeMapper.class);

    MessageVolumeDTO converteParaMessageVolumeDTO(FormVolumeDTO formVolume);
}
