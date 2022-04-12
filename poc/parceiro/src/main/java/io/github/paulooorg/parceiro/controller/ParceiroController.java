package io.github.paulooorg.parceiro.controller;

import io.github.paulooorg.parceiro.controller.dto.request.FormAtribuicaoPedidoDTO;
import io.github.paulooorg.parceiro.controller.dto.request.FormParceiroDTO;
import io.github.paulooorg.parceiro.entity.Parceiro;
import io.github.paulooorg.parceiro.integration.dto.PedidoSimplificadoDTO;
import io.github.paulooorg.parceiro.service.ParceiroService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParceiroController {

    private final ParceiroService parceiroService;

    @Autowired
    public ParceiroController(ParceiroService parceiroService) {
        this.parceiroService = parceiroService;
    }

    @PostMapping
    public ResponseEntity<Long> criarParceiro(@Valid @RequestBody FormParceiroDTO formParceiro) {
        Parceiro parceiro = parceiroService.criarParceiro(formParceiro);
        return ResponseEntity.status(HttpStatus.CREATED).body(parceiro.getId());
    }

    @GetMapping("/{id}/pedidos/disponiveis")
    public ResponseEntity<Page<PedidoSimplificadoDTO>> buscarPedidosDisponiveisParaParceiro(
        @PathVariable("id") Long parceiroId,
        @PageableDefault Pageable pageable
    ) {
        return ResponseEntity.ok(parceiroService.buscarPedidosDisponiveisParaParceiro(parceiroId, pageable));
    }

    @PostMapping("/{id}/atribuir/pedidos")
    public ResponseEntity<Void> atribuirPedidosAoParceiro(
        @PathVariable("id") Long parceiroId,
        @Valid @RequestBody FormAtribuicaoPedidoDTO formAtribuicaoPedido
    ) {
        parceiroService.atribuirPedidosAoParceiro(parceiroId, formAtribuicaoPedido);
        return ResponseEntity.noContent().build();
    }
}
