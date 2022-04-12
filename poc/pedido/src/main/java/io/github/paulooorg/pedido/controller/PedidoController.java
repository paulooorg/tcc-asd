package io.github.paulooorg.pedido.controller;

import io.github.paulooorg.pedido.controller.dto.response.PedidoDTO;
import io.github.paulooorg.pedido.controller.dto.response.PedidoSimplificadoDTO;
import io.github.paulooorg.pedido.entity.SituacaoPedido;
import io.github.paulooorg.pedido.service.PedidoService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/codigo")
    public ResponseEntity<PedidoDTO> buscarPedidoPorCodigo(@RequestParam("codigo") String codigo) {
        return ResponseEntity.ok(pedidoService.buscarPedidoPorCodigo(codigo));
    }

    @GetMapping("/clientes/{clienteId}")
    public ResponseEntity<Page<PedidoSimplificadoDTO>> buscarPedidosDoCliente(
        @PathVariable("clienteId") Long clienteId,
        @RequestParam(value = "situacao", required = false) SituacaoPedido situacao,
        @PageableDefault Pageable pageable
    ) {
        return ResponseEntity.ok(pedidoService.buscarPedidosDoCliente(clienteId, situacao, pageable));
    }

    @PatchMapping("/{pedidoId}/deposito/{depositoId}")
    public ResponseEntity<Void> adicionarPedidoNoDeposito(
        @PathVariable("pedidoId") Long pedidoId,
        @PathVariable("depositoId") Long depositoId
    ) {
        pedidoService.adicionarPedidoNoDeposito(pedidoId, depositoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/situacao/recebido/depositos")
    public ResponseEntity<Page<PedidoSimplificadoDTO>> buscarPedidosRecebidosNosDepositos(
        @RequestParam("depositoIds") Set<Long> depositoIds,
        @PageableDefault Pageable pageable
    ) {
        return ResponseEntity.ok(pedidoService.buscarPedidosRecebidosNosDepositos(depositoIds, pageable));
    }
}
