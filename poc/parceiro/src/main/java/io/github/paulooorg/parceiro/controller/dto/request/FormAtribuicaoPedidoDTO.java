package io.github.paulooorg.parceiro.controller.dto.request;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FormAtribuicaoPedidoDTO {

    private Set<Long> pedidoIds;
}
