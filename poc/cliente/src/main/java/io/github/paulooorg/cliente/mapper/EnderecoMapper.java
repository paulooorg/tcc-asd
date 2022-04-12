package io.github.paulooorg.cliente.mapper;

import io.github.paulooorg.cliente.controller.dto.request.FormCalcularFreteDTO;
import io.github.paulooorg.cliente.controller.dto.request.FormEnderecoDTO;
import io.github.paulooorg.cliente.controller.dto.response.EnderecoDTO;
import io.github.paulooorg.cliente.entity.Endereco;
import io.github.paulooorg.cliente.producer.dto.MessageEnderecoDTO;
import io.github.paulooorg.cliente.producer.dto.MessageVolumeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnderecoMapper {
    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    EnderecoDTO converteParaEnderecoDTO(Endereco endereco);

    MessageEnderecoDTO converteParaMessageEnderecoDTO(Endereco endereco);

    MessageEnderecoDTO converteParaMessageEnderecoDTO(FormEnderecoDTO formEndereco);
}
