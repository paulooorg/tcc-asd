package io.github.paulooorg.entrega.controller.dto.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FormSucessoEntregaDTO {

    private String codigoPedido;

    private String responsavelEntrega;

    private Long assinaturaId;

    private String observacao;

    private BigDecimal latitude;

    private BigDecimal longitude;
}
