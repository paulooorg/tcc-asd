package io.github.paulooorg.deposito.repository;

import io.github.paulooorg.deposito.entity.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {}
