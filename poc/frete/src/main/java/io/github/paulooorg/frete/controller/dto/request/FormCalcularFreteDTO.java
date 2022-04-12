package io.github.paulooorg.frete.controller.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FormCalcularFreteDTO {

    private Integer cepOrigem;

    private Integer cepDestino;

    private List<FormVolumeDTO> volumes;

    private List<FormServicoOpcionalDTO> servicosOpcionais;
}
