package io.github.paulooorg.cliente.producer.dto;

import io.github.paulooorg.cliente.entity.FormatoVolume;
import io.github.paulooorg.cliente.entity.UnidadeMedida;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@ToString
public class MessageVolumeDTO implements Serializable {

    private FormatoVolume formato;

    private BigDecimal peso;

    private UnidadeMedida unidadeMedidaPeso;

    private BigDecimal altura;

    private BigDecimal largura;

    private BigDecimal comprimento;

    private Integer quantidade;
}
