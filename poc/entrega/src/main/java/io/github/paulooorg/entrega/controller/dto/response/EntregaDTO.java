package io.github.paulooorg.entrega.controller.dto.response;

import io.github.paulooorg.entrega.entity.MotivoEntrega;
import io.github.paulooorg.entrega.entity.SituacaoEntrega;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EntregaDTO {

    private Long id;

    private String codigoPedido;

    private String responsavelEntrega;

    private LocalDateTime dataEntrega;

    private SituacaoEntrega situacao;

    private MotivoEntrega motivo;

    private String observacao;

    private BigDecimal latitude;

    private BigDecimal longitude;
}
