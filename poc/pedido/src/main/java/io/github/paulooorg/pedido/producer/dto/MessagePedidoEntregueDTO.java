package io.github.paulooorg.pedido.producer.dto;

import java.io.Serializable;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessagePedidoEntregueDTO implements Serializable {

    private String codigo;
}
