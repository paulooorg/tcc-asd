package io.github.paulooorg.pedido.mapper;

import io.github.paulooorg.pedido.consumer.dto.FormVolumeDTO;
import io.github.paulooorg.pedido.entity.Volume;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VolumeMapper {
    VolumeMapper INSTANCE = Mappers.getMapper(VolumeMapper.class);

    Volume converteParaVolume(FormVolumeDTO formVolume);
}
