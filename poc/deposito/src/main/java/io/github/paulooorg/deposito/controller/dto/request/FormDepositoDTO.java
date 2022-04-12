package io.github.paulooorg.deposito.controller.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FormDepositoDTO {

    private String nome;

    private FormEnderecoDTO endereco;
}
