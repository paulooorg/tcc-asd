package io.github.paulooorg.cliente.controller.dto.request;

import io.github.paulooorg.cliente.entity.TipoServicoOpcional;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FormServicoOpcionalDTO {

    private TipoServicoOpcional tipo;

    private Map<String, Object> parametros;
}
