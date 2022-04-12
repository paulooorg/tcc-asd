package io.github.paulooorg.parceiro.repository;

import io.github.paulooorg.parceiro.entity.Parceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParceiroRepository extends JpaRepository<Parceiro, Long> {}
