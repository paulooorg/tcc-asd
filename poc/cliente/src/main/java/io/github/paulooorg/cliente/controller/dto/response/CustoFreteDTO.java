package io.github.paulooorg.cliente.controller.dto.response;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustoFreteDTO {

    private Integer prazoDiasUteis;

    private BigDecimal custoTotal;

    private BigDecimal custoFrete;

    private BigDecimal custoServicos;

    private BigDecimal descontoCliente;
}
