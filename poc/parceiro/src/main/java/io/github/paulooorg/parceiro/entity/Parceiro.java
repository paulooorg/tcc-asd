package io.github.paulooorg.parceiro.entity;

import java.util.Set;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Parceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ElementCollection
    @CollectionTable(name = "parceiro_deposito", joinColumns = @JoinColumn(name = "parceiro_id"))
    @Column(name = "deposito_id")
    private Set<Long> depositos;
}
