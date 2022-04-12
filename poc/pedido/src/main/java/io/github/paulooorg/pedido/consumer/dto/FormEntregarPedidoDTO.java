package io.github.paulooorg.pedido.consumer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FormEntregarPedidoDTO {

    private String codigoPedido;

    private boolean entregue;
}
