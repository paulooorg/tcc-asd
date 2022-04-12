package io.github.paulooorg.frete.service;

import io.github.paulooorg.frete.controller.dto.request.FormCalcularFreteDTO;
import io.github.paulooorg.frete.controller.dto.response.CustoFreteDTO;

public interface FreteService {
    CustoFreteDTO calcular(FormCalcularFreteDTO formCalcularFrete);
}
