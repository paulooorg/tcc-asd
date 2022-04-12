package io.github.paulooorg.pedido.mapper;

import io.github.paulooorg.pedido.controller.dto.response.PedidoDTO;
import io.github.paulooorg.pedido.controller.dto.response.PedidoSimplificadoDTO;
import io.github.paulooorg.pedido.entity.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PedidoMapper {
    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

    PedidoDTO converteParaPedidoDTO(Pedido pedido);

    PedidoSimplificadoDTO converteParaPedidoSimplificadoDTO(Pedido pedido);
}
