package io.github.paulooorg.parceiro.integration.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PedidoSimplificadoDTO {

    private Long id;

    private String codigo;

    private LocalDateTime dataEmissao;

    private LocalDate dataEntregaPrevista;

    private BigDecimal custoFrete;

    private EnderecoDTO enderecoDestinatario;

    private String responsavelEntrega;

    private String situacao;

    private EnderecoDTO enderecoRemetente;

    private Long depositoId;
}
