package io.github.paulooorg.parceiro.producer.dto;

import java.util.Set;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessageAtribuirPedidoDTO {

    private String nomeResponsavel;

    private Set<Long> pedidoIds;
}
