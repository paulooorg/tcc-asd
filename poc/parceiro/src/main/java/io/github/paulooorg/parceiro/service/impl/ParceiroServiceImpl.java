package io.github.paulooorg.parceiro.service.impl;

import io.github.paulooorg.parceiro.controller.dto.request.FormAtribuicaoPedidoDTO;
import io.github.paulooorg.parceiro.controller.dto.request.FormParceiroDTO;
import io.github.paulooorg.parceiro.entity.Parceiro;
import io.github.paulooorg.parceiro.integration.PedidoClient;
import io.github.paulooorg.parceiro.integration.dto.PedidoSimplificadoDTO;
import io.github.paulooorg.parceiro.mapper.ParceiroMapper;
import io.github.paulooorg.parceiro.producer.PedidoProducer;
import io.github.paulooorg.parceiro.producer.dto.MessageAtribuirPedidoDTO;
import io.github.paulooorg.parceiro.repository.ParceiroRepository;
import io.github.paulooorg.parceiro.service.ParceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ParceiroServiceImpl implements ParceiroService {

    private final ParceiroRepository parceiroRepository;

    private final PedidoClient pedidoClient;

    private final PedidoProducer pedidoProducer;

    @Autowired
    public ParceiroServiceImpl(
        ParceiroRepository parceiroRepository,
        PedidoClient pedidoClient,
        PedidoProducer pedidoProducer
    ) {
        this.parceiroRepository = parceiroRepository;
        this.pedidoClient = pedidoClient;
        this.pedidoProducer = pedidoProducer;
    }

    @Override
    public Parceiro criarParceiro(FormParceiroDTO formParceiro) {
        Parceiro parceiro = ParceiroMapper.INSTANCE.converteParaParceiro(formParceiro);
        return parceiroRepository.save(parceiro);
    }

    @Override
    public Page<PedidoSimplificadoDTO> buscarPedidosDisponiveisParaParceiro(Long parceiroId, Pageable pageable) {
        Parceiro parceiro = parceiroRepository.findById(parceiroId).get();
        return pedidoClient.buscarPedidosRecebidosNosDepositos(parceiro.getDepositos(), pageable);
    }

    @Override
    public void atribuirPedidosAoParceiro(Long parceiroId, FormAtribuicaoPedidoDTO formAtribuicaoPedido) {
        Parceiro parceiro = parceiroRepository.findById(parceiroId).get();
        pedidoProducer.atribuirPedido(
            new MessageAtribuirPedidoDTO(parceiro.getNome(), formAtribuicaoPedido.getPedidoIds())
        );
    }
}
