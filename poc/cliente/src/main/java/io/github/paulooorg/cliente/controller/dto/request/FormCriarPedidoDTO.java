package io.github.paulooorg.cliente.controller.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FormCriarPedidoDTO {

    private Long enderecoId;

    private String nomeDestinatario;

    private String documentoDestinatario;

    private FormEnderecoDTO enderecoDestinatario;

    private List<FormVolumeDTO> volumes;

    private List<FormServicoOpcionalDTO> servicosOpcionais;
}
