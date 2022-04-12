package io.github.paulooorg.deposito.controller.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DepositoDTO {

    private Long id;

    private String nome;

    private EnderecoDTO endereco;
}
