package io.github.paulooorg.parceiro.service;

import io.github.paulooorg.parceiro.controller.dto.request.FormAtribuicaoPedidoDTO;
import io.github.paulooorg.parceiro.controller.dto.request.FormParceiroDTO;
import io.github.paulooorg.parceiro.entity.Parceiro;
import io.github.paulooorg.parceiro.integration.dto.PedidoSimplificadoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParceiroService {
    Parceiro criarParceiro(FormParceiroDTO formParceiro);

    Page<PedidoSimplificadoDTO> buscarPedidosDisponiveisParaParceiro(Long parceiroId, Pageable pageable);

    void atribuirPedidosAoParceiro(Long parceiroId, FormAtribuicaoPedidoDTO formAtribuicaoPedido);
}
