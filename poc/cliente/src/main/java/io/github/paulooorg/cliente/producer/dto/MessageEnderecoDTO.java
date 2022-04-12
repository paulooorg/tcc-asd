package io.github.paulooorg.cliente.producer.dto;

import java.io.Serializable;
import lombok.*;

@Getter
@Setter
@ToString
public class MessageEnderecoDTO implements Serializable {

    private String endereco;

    private String cidade;

    private String estado;

    private Integer cep;

    private String complemento;

    private String contato;
}
