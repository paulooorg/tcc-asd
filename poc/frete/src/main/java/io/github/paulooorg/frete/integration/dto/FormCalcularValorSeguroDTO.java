package io.github.paulooorg.frete.integration.dto;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormCalcularValorSeguroDTO {

    private Integer cepOrigem;

    private Integer cepDestino;

    private BigDecimal valorDeclarado;
}
