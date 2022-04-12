package io.github.paulooorg.entrega.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoPedido;

    private String responsavelEntrega;

    @Builder.Default
    private LocalDateTime dataEntrega = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private SituacaoEntrega situacao;

    @Enumerated(EnumType.STRING)
    private MotivoEntrega motivo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assinatura_id")
    private Assinatura assinatura;

    private String observacao;

    private BigDecimal latitude;

    private BigDecimal longitude;
}
