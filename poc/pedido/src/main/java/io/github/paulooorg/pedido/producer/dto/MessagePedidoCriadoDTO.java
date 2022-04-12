package io.github.paulooorg.pedido.producer.dto;

import java.io.Serializable;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessagePedidoCriadoDTO implements Serializable {

    private String codigo;
}
