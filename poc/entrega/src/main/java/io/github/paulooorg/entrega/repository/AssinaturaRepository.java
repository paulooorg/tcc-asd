package io.github.paulooorg.entrega.repository;

import io.github.paulooorg.entrega.entity.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {}
