package io.github.paulooorg.entrega.controller;

import io.github.paulooorg.entrega.controller.dto.request.FormFalhaEntregaDTO;
import io.github.paulooorg.entrega.controller.dto.request.FormSucessoEntregaDTO;
import io.github.paulooorg.entrega.controller.dto.response.EntregaDTO;
import io.github.paulooorg.entrega.entity.Assinatura;
import io.github.paulooorg.entrega.entity.Entrega;
import io.github.paulooorg.entrega.service.EntregaService;
import java.io.IOException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EntregaController {

    private final EntregaService entregaService;

    @Autowired
    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @PostMapping("/entregar/situacao/entregue")
    public ResponseEntity<Long> criarEntregaComoEntregue(@Valid @RequestBody FormSucessoEntregaDTO formSucessoEntrega) {
        Entrega entrega = entregaService.criarEntregaComoEntregue(formSucessoEntrega);
        return ResponseEntity.status(HttpStatus.CREATED).body(entrega.getId());
    }

    @PostMapping("/entregar/situacao/nao-entregue")
    public ResponseEntity<Long> criarEntregaComoNaoEntregue(@Valid @RequestBody FormFalhaEntregaDTO formFalhaEntrega) {
        Entrega entrega = entregaService.criarEntregaComoNaoEntregue(formFalhaEntrega);
        return ResponseEntity.status(HttpStatus.CREATED).body(entrega.getId());
    }

    @GetMapping("/pedido")
    public ResponseEntity<EntregaDTO> buscarEntregaPorCodigoDoPedido(@RequestParam("codigo") String codigo) {
        return ResponseEntity.ok(entregaService.buscarEntregaPorCodigoDoPedido(codigo));
    }

    @GetMapping("/{id}/assinatura")
    public ResponseEntity<Resource> buscarAssinaturaDaEntrega(@PathVariable("id") Long entregaId) {
        Resource assinatura = entregaService.buscarAssinaturaDaEntrega(entregaId);

        HttpHeaders headers = new HttpHeaders();
        Long contentLength = 0L;
        try {
            contentLength = assinatura.contentLength();
        } catch (IOException e) {
            e.printStackTrace();
        }
        headers.setContentLength(contentLength);
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "assinatura.png");

        return ResponseEntity.ok().headers(headers).body(assinatura);
    }

    @PostMapping("/assinatura")
    public ResponseEntity<Long> criarAssinatura(@RequestParam("assinatura") MultipartFile arquivo) {
        Assinatura assinatura = entregaService.criarAssinatura(arquivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(assinatura.getId());
    }
}
