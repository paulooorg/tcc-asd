package io.github.paulooorg.deposito.mapper;

import io.github.paulooorg.deposito.controller.dto.request.FormDepositoDTO;
import io.github.paulooorg.deposito.controller.dto.response.DepositoDTO;
import io.github.paulooorg.deposito.entity.Deposito;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepositoMapper {
    DepositoMapper INSTANCE = Mappers.getMapper(DepositoMapper.class);

    DepositoDTO converteParaDepositoDTO(Deposito deposito);

    Deposito converteParaDeposito(FormDepositoDTO formDeposito);
}
