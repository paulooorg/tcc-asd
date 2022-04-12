package io.github.paulooorg.entrega.producer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageEntregarPedidoDTO {

    private String codigoPedido;

    private boolean entregue;
}
