package io.github.paulooorg.cliente.producer.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageCriarPedidoDTO implements Serializable {

    private Long clienteId;

    private Integer prazoDiasUteis;

    private BigDecimal custoFrete;

    private String nomeDestinatario;

    private String documentoDestinatario;

    private MessageEnderecoDTO enderecoDestinatario;

    private List<MessageVolumeDTO> volumes;

    private String nomeRemetente;

    private MessageEnderecoDTO enderecoRemetente;
}
