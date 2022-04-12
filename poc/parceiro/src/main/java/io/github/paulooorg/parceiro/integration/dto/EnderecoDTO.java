package io.github.paulooorg.parceiro.integration.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EnderecoDTO {

    private String endereco;

    private String cidade;

    private String estado;

    private Integer cep;

    private String complemento;

    private String contato;
}
