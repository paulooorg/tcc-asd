package io.github.paulooorg.pedido.service;

import io.github.paulooorg.pedido.consumer.dto.FormAtribuicaoPedidoDTO;
import io.github.paulooorg.pedido.consumer.dto.FormCriarPedidoDTO;
import io.github.paulooorg.pedido.consumer.dto.FormEntregarPedidoDTO;
import io.github.paulooorg.pedido.controller.dto.response.PedidoDTO;
import io.github.paulooorg.pedido.controller.dto.response.PedidoSimplificadoDTO;
import io.github.paulooorg.pedido.entity.Pedido;
import io.github.paulooorg.pedido.entity.SituacaoPedido;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoService {
    Pedido criar(FormCriarPedidoDTO formCriarPedido);

    Page<PedidoSimplificadoDTO> buscarPedidosDoCliente(Long clienteId, SituacaoPedido situacao, Pageable pageable);

    PedidoDTO buscarPedidoPorCodigo(String codigo);

    void adicionarPedidoNoDeposito(Long pedidoId, Long depositoId);

    Page<PedidoSimplificadoDTO> buscarPedidosRecebidosNosDepositos(Set<Long> depositoIds, Pageable pageable);

    void atribuirPedido(FormAtribuicaoPedidoDTO formAtribuirPedido);

    void entregarPedido(FormEntregarPedidoDTO formEntregarPedido);
}
