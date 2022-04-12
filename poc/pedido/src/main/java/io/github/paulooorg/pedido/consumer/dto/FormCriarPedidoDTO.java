package io.github.paulooorg.pedido.consumer.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FormCriarPedidoDTO {

    private Long clienteId;

    private Integer prazoDiasUteis;

    private BigDecimal custoFrete;

    private String nomeDestinatario;

    private String documentoDestinatario;

    private FormEnderecoDTO enderecoDestinatario;

    private List<FormVolumeDTO> volumes;

    private String nomeRemetente;

    private FormEnderecoDTO enderecoRemetente;
}
