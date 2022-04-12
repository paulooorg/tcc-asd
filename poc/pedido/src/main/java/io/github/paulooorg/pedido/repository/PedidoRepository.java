package io.github.paulooorg.pedido.repository;

import io.github.paulooorg.pedido.entity.Pedido;
import io.github.paulooorg.pedido.entity.SituacaoPedido;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByCodigo(String codigo);

    Page<Pedido> findAllByClienteId(Long clienteId, Pageable pageable);

    Page<Pedido> findAllByClienteIdAndSituacao(Long clienteId, SituacaoPedido situacaoPedido, Pageable pageable);

    Page<Pedido> findAllBySituacaoAndDepositoIdIn(SituacaoPedido situacao, Set<Long> depositoIds, Pageable pageable);
}
