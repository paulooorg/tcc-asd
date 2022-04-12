package io.github.paulooorg.cliente.controller.dto.request;

import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormCalcularFreteDTO {

    private Integer cepOrigem;

    private Integer cepDestino;

    private List<FormVolumeDTO> volumes;

    private List<FormServicoOpcionalDTO> servicosOpcionais;
}
