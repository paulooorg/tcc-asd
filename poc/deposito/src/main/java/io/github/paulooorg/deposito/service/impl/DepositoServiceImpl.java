package io.github.paulooorg.deposito.service.impl;

import io.github.paulooorg.deposito.controller.dto.request.FormDepositoDTO;
import io.github.paulooorg.deposito.controller.dto.response.DepositoDTO;
import io.github.paulooorg.deposito.entity.Deposito;
import io.github.paulooorg.deposito.mapper.DepositoMapper;
import io.github.paulooorg.deposito.repository.DepositoRepository;
import io.github.paulooorg.deposito.service.DepositoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DepositoServiceImpl implements DepositoService {

    private final DepositoRepository depositoRepository;

    @Autowired
    public DepositoServiceImpl(DepositoRepository depositoRepository) {
        this.depositoRepository = depositoRepository;
    }

    @Override
    public DepositoDTO buscarDepositoPorId(Long id) {
        Deposito deposito = depositoRepository.findById(id).get();
        return DepositoMapper.INSTANCE.converteParaDepositoDTO(deposito);
    }

    @Override
    public Page<DepositoDTO> buscarDepositos(Pageable pageable) {
        Page<Deposito> depositos = depositoRepository.findAll(pageable);
        return depositos.map(DepositoMapper.INSTANCE::converteParaDepositoDTO);
    }

    @Override
    public Deposito criarDeposito(FormDepositoDTO formDeposito) {
        Deposito deposito = DepositoMapper.INSTANCE.converteParaDeposito(formDeposito);
        deposito.getEndereco().setDeposito(deposito);
        return depositoRepository.save(deposito);
    }
}
