package io.github.paulooorg.frete.controller.dto.request;

import io.github.paulooorg.frete.entity.TipoServicoOpcional;
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
