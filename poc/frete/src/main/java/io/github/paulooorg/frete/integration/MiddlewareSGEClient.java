package io.github.paulooorg.frete.integration;

import io.github.paulooorg.frete.integration.dto.CustoSeguroDTO;
import io.github.paulooorg.frete.integration.dto.FormCalcularValorSeguroDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "middleware-sge", url = "http://localhost:8090/api/middleware-sge")
public interface MiddlewareSGEClient {
    @PostMapping("/seguros/calcular")
    CustoSeguroDTO calcularSeguro(FormCalcularValorSeguroDTO formCalcularValorSeguro);
}
