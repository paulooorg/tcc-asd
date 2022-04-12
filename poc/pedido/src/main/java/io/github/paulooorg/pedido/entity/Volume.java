package io.github.paulooorg.pedido.entity;

import java.math.BigDecimal;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Volume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private FormatoVolume formato;

    private BigDecimal peso;

    private UnidadeMedida unidadeMedidaPeso;

    private BigDecimal altura;

    private BigDecimal largura;

    private BigDecimal comprimento;

    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
