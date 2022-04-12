package io.github.paulooorg.pedido.mapper;

import io.github.paulooorg.pedido.consumer.dto.FormEnderecoDTO;
import io.github.paulooorg.pedido.entity.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnderecoMapper {
    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    Endereco converteParaEndereco(FormEnderecoDTO formEndereco);
}
