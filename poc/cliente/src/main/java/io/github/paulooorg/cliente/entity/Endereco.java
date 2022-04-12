package io.github.paulooorg.cliente.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
