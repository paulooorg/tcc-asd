package io.github.paulooorg.middlewaresge.integration.dto.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FormCalcularValorSeguroDTO {

    private Integer cepOrigem;

    private Integer cepDestino;

    private BigDecimal valorDeclarado;
}
