package io.github.paulooorg.pedido.consumer.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FormAtribuicaoPedidoDTO {

    private String nomeResponsavel;

    private Set<Long> pedidoIds;
}
