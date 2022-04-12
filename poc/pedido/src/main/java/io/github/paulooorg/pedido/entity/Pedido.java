package io.github.paulooorg.pedido.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;

    @Builder.Default
    private String codigo = UUID.randomUUID().toString();

    @Builder.Default
    private LocalDateTime dataEmissao = LocalDateTime.now();

    private LocalDate dataEntregaPrevista;

    private BigDecimal custoFrete;

    private String nomeDestinatario;

    private String documentoDestinatario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_destinatario_id", referencedColumnName = "id")
    private Endereco enderecoDestinatario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Volume> volumes;

    private String responsavelEntrega;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private SituacaoPedido situacao = SituacaoPedido.PEDIDO_CRIADO;

    private String nomeRemetente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_remetente_id", referencedColumnName = "id")
    private Endereco enderecoRemetente;

    private Long depositoId;

    public void addVolume(Volume volume) {
        if (volumes == null) {
            volumes = new ArrayList<>();
        }
        volume.setPedido(this);
        volumes.add(volume);
    }
}
