package io.github.paulooorg.cliente.controller;

import io.github.paulooorg.cliente.controller.dto.request.FormCalcularFreteDTO;
import io.github.paulooorg.cliente.controller.dto.request.FormCriarPedidoDTO;
import io.github.paulooorg.cliente.controller.dto.response.CustoFreteDTO;
import io.github.paulooorg.cliente.controller.dto.response.EnderecoDTO;
import io.github.paulooorg.cliente.service.ClienteService;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/{id}/fretes/calcular")
    public ResponseEntity<CustoFreteDTO> calcularFrete(
        @PathVariable("id") Long clienteId,
        @Valid @RequestBody FormCalcularFreteDTO formCalcularFrete
    ) {
        return ResponseEntity.ok(clienteService.calcularFrete(clienteId, formCalcularFrete));
    }

    @RolesAllowed({ "cliente" })
    @PostMapping("/{id}/pedidos")
    public ResponseEntity<Void> criarPedido(
        @PathVariable("id") Long clienteId,
        @Valid @RequestBody FormCriarPedidoDTO formCriarPedido
    ) {
        clienteService.criarPedido(clienteId, formCriarPedido);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/enderecos")
    public ResponseEntity<List<EnderecoDTO>> buscarEnderecosDoCliente(@PathVariable("id") Long clienteId) {
        return ResponseEntity.ok(clienteService.buscarEnderecosDoCliente(clienteId));
    }
}
