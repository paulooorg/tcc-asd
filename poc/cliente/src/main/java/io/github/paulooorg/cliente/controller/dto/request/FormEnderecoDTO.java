package io.github.paulooorg.cliente.controller.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FormEnderecoDTO {

    private String endereco;

    private String cidade;

    private String estado;

    private Integer cep;

    private String complemento;

    private String contato;
}
