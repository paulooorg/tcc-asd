package io.github.paulooorg.deposito.service;

import io.github.paulooorg.deposito.controller.dto.request.FormDepositoDTO;
import io.github.paulooorg.deposito.controller.dto.response.DepositoDTO;
import io.github.paulooorg.deposito.entity.Deposito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepositoService {
    DepositoDTO buscarDepositoPorId(Long id);

    Page<DepositoDTO> buscarDepositos(Pageable pageable);

    Deposito criarDeposito(FormDepositoDTO formDeposito);
}
