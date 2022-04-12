package io.github.paulooorg.pedido.controller.dto.response;

import io.github.paulooorg.pedido.entity.FormatoVolume;
import io.github.paulooorg.pedido.entity.UnidadeMedida;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VolumeDTO {

    private FormatoVolume formato;

    private BigDecimal peso;

    private UnidadeMedida unidadeMedidaPeso;

    private BigDecimal altura;

    private BigDecimal largura;

    private BigDecimal comprimento;

    private Integer quantidade;
}
