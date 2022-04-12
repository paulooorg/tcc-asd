package io.github.paulooorg.parceiro.integration;

import io.github.paulooorg.parceiro.integration.dto.PedidoSimplificadoDTO;
import java.util.Set;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pedido", url = "http://localhost:8087/api/pedidos")
public interface PedidoClient {
    @GetMapping("/situacao/recebido/depositos")
    Page<PedidoSimplificadoDTO> buscarPedidosRecebidosNosDepositos(
        @RequestParam("depositoIds") Set<Long> depositoIds,
        @SpringQueryMap Pageable pageable
    );
}
