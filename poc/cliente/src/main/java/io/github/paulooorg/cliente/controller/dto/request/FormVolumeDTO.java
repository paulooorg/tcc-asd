package io.github.paulooorg.cliente.controller.dto.request;

import io.github.paulooorg.cliente.entity.FormatoVolume;
import io.github.paulooorg.cliente.entity.UnidadeMedida;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FormVolumeDTO {

    private FormatoVolume formato;

    private BigDecimal peso;

    private UnidadeMedida unidadeMedidaPeso;

    private BigDecimal altura;

    private BigDecimal largura;

    private BigDecimal comprimento;

    private Integer quantidade;
}
