package io.github.paulooorg.entrega.service.impl;

import io.github.paulooorg.entrega.controller.dto.request.FormFalhaEntregaDTO;
import io.github.paulooorg.entrega.controller.dto.request.FormSucessoEntregaDTO;
import io.github.paulooorg.entrega.controller.dto.response.EntregaDTO;
import io.github.paulooorg.entrega.entity.Assinatura;
import io.github.paulooorg.entrega.entity.Entrega;
import io.github.paulooorg.entrega.entity.SituacaoEntrega;
import io.github.paulooorg.entrega.mapper.EntregaMapper;
import io.github.paulooorg.entrega.producer.PedidoProducer;
import io.github.paulooorg.entrega.producer.dto.MessageEntregarPedidoDTO;
import io.github.paulooorg.entrega.repository.AssinaturaRepository;
import io.github.paulooorg.entrega.repository.EntregaRepository;
import io.github.paulooorg.entrega.service.EntregaService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EntregaServiceImpl implements EntregaService {

    private final EntregaRepository entregaRepository;

    private final PedidoProducer pedidoProducer;

    private final AssinaturaRepository assinaturaRepository;

    @Autowired
    public EntregaServiceImpl(
        EntregaRepository entregaRepository,
        PedidoProducer pedidoProducer,
        AssinaturaRepository assinaturaRepository
    ) {
        this.entregaRepository = entregaRepository;
        this.pedidoProducer = pedidoProducer;
        this.assinaturaRepository = assinaturaRepository;
    }

    @Override
    public Entrega criarEntregaComoEntregue(FormSucessoEntregaDTO formSucessoEntrega) {
        Assinatura assinatura = assinaturaRepository.findById(formSucessoEntrega.getAssinaturaId()).get();
        Entrega entrega = Entrega
            .builder()
            .codigoPedido(formSucessoEntrega.getCodigoPedido())
            .responsavelEntrega(formSucessoEntrega.getResponsavelEntrega())
            .situacao(SituacaoEntrega.ENTREGUE)
            .observacao(formSucessoEntrega.getObservacao())
            .assinatura(assinatura)
            .latitude(formSucessoEntrega.getLatitude())
            .longitude(formSucessoEntrega.getLongitude())
            .build();
        entregaRepository.save(entrega);
        pedidoProducer.entregarPedido(
            MessageEntregarPedidoDTO.builder().codigoPedido(entrega.getCodigoPedido()).entregue(true).build()
        );
        return entrega;
    }

    private byte[] getContent(MultipartFile file) {
        byte[] content = new byte[0];
        try {
            content = file.getBytes();
        } catch (IOException e) {}
        return content;
    }

    @Override
    public Entrega criarEntregaComoNaoEntregue(FormFalhaEntregaDTO formFalhaEntrega) {
        Entrega entrega = Entrega
            .builder()
            .codigoPedido(formFalhaEntrega.getCodigoPedido())
            .responsavelEntrega(formFalhaEntrega.getResponsavelEntrega())
            .situacao(SituacaoEntrega.NAO_ENTREGUE)
            .motivo(formFalhaEntrega.getMotivo())
            .observacao(formFalhaEntrega.getObservacao())
            .latitude(formFalhaEntrega.getLatitude())
            .longitude(formFalhaEntrega.getLongitude())
            .build();
        entregaRepository.save(entrega);
        pedidoProducer.entregarPedido(
            MessageEntregarPedidoDTO.builder().codigoPedido(entrega.getCodigoPedido()).entregue(false).build()
        );
        return entrega;
    }

    @Override
    public EntregaDTO buscarEntregaPorCodigoDoPedido(String codigo) {
        Entrega entrega = entregaRepository.findByCodigoPedido(codigo).get();
        return EntregaMapper.INSTANCE.converteParaEntregaDTO(entrega);
    }

    @Override
    public Resource buscarAssinaturaDaEntrega(Long entregaId) {
        Entrega entrega = entregaRepository.findById(entregaId).get();
        return new ByteArrayResource(entrega.getAssinatura().getArquivo());
    }

    @Override
    public Assinatura criarAssinatura(MultipartFile arquivo) {
        Assinatura assinatura = new Assinatura(getContent(arquivo));
        return assinaturaRepository.save(assinatura);
    }
}
