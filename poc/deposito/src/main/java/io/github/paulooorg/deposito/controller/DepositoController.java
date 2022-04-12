package io.github.paulooorg.deposito.controller;

import io.github.paulooorg.deposito.controller.dto.request.FormDepositoDTO;
import io.github.paulooorg.deposito.controller.dto.response.DepositoDTO;
import io.github.paulooorg.deposito.entity.Deposito;
import io.github.paulooorg.deposito.service.DepositoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepositoController {

    private final DepositoService depositoService;

    @Autowired
    public DepositoController(DepositoService depositoService) {
        this.depositoService = depositoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepositoDTO> buscarDepositoPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(depositoService.buscarDepositoPorId(id));
    }

    @GetMapping
    public ResponseEntity<Page<DepositoDTO>> buscarDepositos(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(depositoService.buscarDepositos(pageable));
    }

    @PostMapping
    public ResponseEntity<Long> criarDeposito(@Valid @RequestBody FormDepositoDTO formDeposito) {
        Deposito deposito = depositoService.criarDeposito(formDeposito);
        return ResponseEntity.status(HttpStatus.CREATED).body(deposito.getId());
    }
}
