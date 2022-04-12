package io.github.paulooorg.pedido.controller.dto.response;

import io.github.paulooorg.pedido.entity.SituacaoPedido;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PedidoDTO {

    private Long id;

    private String codigo;

    private LocalDateTime dataEmissao;

    private LocalDate dataEntregaPrevista;

    private BigDecimal custoFrete;

    private String nomeDestinatario;

    private String documentoDestinatario;

    private EnderecoDTO enderecoDestinatario;

    private List<VolumeDTO> volumes;

    private String responsavelEntrega;

    private SituacaoPedido situacao;

    private String nomeRemetente;

    private EnderecoDTO enderecoRemetente;

    private Long depositoId;
}
