package io.github.paulooorg.entrega.controller.dto.request;

import io.github.paulooorg.entrega.entity.MotivoEntrega;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FormFalhaEntregaDTO {

    private String codigoPedido;

    private String responsavelEntrega;

    private MotivoEntrega motivo;

    private String observacao;

    private BigDecimal latitude;

    private BigDecimal longitude;
}
