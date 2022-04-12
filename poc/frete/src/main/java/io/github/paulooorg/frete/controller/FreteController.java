package io.github.paulooorg.frete.controller;

import io.github.paulooorg.frete.controller.dto.request.FormCalcularFreteDTO;
import io.github.paulooorg.frete.controller.dto.response.CustoFreteDTO;
import io.github.paulooorg.frete.service.FreteService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FreteController {

    private final FreteService freteService;

    @Autowired
    public FreteController(FreteService freteService) {
        this.freteService = freteService;
    }

    @PostMapping("/calcular")
    public ResponseEntity<CustoFreteDTO> calcular(@Valid @RequestBody FormCalcularFreteDTO formCalcularFrete) {
        return ResponseEntity.ok(freteService.calcular(formCalcularFrete));
    }
}
