package io.github.paulooorg.cliente.integration;

import io.github.paulooorg.cliente.controller.dto.request.FormCalcularFreteDTO;
import io.github.paulooorg.cliente.controller.dto.response.CustoFreteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "frete", url = "http://localhost:8085/api/fretes")
public interface FreteClient {
    @PostMapping("/calcular")
    CustoFreteDTO calcular(FormCalcularFreteDTO formCalcularFrete);
}
