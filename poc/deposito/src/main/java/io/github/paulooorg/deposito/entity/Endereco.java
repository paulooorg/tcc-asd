package io.github.paulooorg.deposito.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endereco;

    private String cidade;

    private String estado;

    private Integer cep;

    private String complemento;

    private String contato;

    @OneToOne(mappedBy = "endereco")
    private Deposito deposito;
}
