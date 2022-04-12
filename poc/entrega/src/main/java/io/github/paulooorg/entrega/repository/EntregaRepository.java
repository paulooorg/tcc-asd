package io.github.paulooorg.entrega.repository;

import io.github.paulooorg.entrega.entity.Entrega;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
    Optional<Entrega> findByCodigoPedido(String codigo);
}
