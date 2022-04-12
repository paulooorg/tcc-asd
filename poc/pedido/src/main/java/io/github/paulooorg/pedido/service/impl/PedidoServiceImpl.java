package io.github.paulooorg.pedido.service.impl;

import io.github.paulooorg.pedido.consumer.dto.FormAtribuicaoPedidoDTO;
import io.github.paulooorg.pedido.consumer.dto.FormCriarPedidoDTO;
import io.github.paulooorg.pedido.consumer.dto.FormEntregarPedidoDTO;
import io.github.paulooorg.pedido.controller.dto.response.PedidoDTO;
import io.github.paulooorg.pedido.controller.dto.response.PedidoSimplificadoDTO;
import io.github.paulooorg.pedido.entity.Pedido;
import io.github.paulooorg.pedido.entity.SituacaoPedido;
import io.github.paulooorg.pedido.mapper.EnderecoMapper;
import io.github.paulooorg.pedido.mapper.PedidoMapper;
import io.github.paulooorg.pedido.mapper.VolumeMapper;
import io.github.paulooorg.pedido.producer.MiddlewareSGEProducer;
import io.github.paulooorg.pedido.producer.dto.MessagePedidoCriadoDTO;
import io.github.paulooorg.pedido.producer.dto.MessagePedidoEntregueDTO;
import io.github.paulooorg.pedido.repository.PedidoRepository;
import io.github.paulooorg.pedido.service.PedidoService;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    private final MiddlewareSGEProducer middlewareSGEProducer;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, MiddlewareSGEProducer middlewareSGEProducer) {
        this.pedidoRepository = pedidoRepository;
        this.middlewareSGEProducer = middlewareSGEProducer;
    }

    @Override
    public void entregarPedido(FormEntregarPedidoDTO formEntregarPedido) {
        Pedido pedido = pedidoRepository.findByCodigo(formEntregarPedido.getCodigoPedido()).get();
        pedido.setResponsavelEntrega(null);
        if (formEntregarPedido.isEntregue()) {
            pedido.setSituacao(SituacaoPedido.ENTREGA_CONCLUIDA);
        } else {
            pedido.setSituacao(SituacaoPedido.ENTREGA_NAO_CONCLUIDA);
        }
        pedidoRepository.save(pedido);
        middlewareSGEProducer.notificarEntregaDoPedido(new MessagePedidoEntregueDTO(pedido.getCodigo()));
    }

    @Override
    public Page<PedidoSimplificadoDTO> buscarPedidosRecebidosNosDepositos(Set<Long> depositoIds, Pageable pageable) {
        Page<Pedido> pedidos = pedidoRepository.findAllBySituacaoAndDepositoIdIn(
            SituacaoPedido.PEDIDO_RECEBIDO,
            depositoIds,
            pageable
        );
        return pedidos.map(PedidoMapper.INSTANCE::converteParaPedidoSimplificadoDTO);
    }

    @Override
    public void atribuirPedido(FormAtribuicaoPedidoDTO formAtribuirPedido) {
        List<Pedido> pedidos = pedidoRepository.findAllById(formAtribuirPedido.getPedidoIds());
        pedidos.forEach(
            pedido -> {
                pedido.setDepositoId(null);
                pedido.setResponsavelEntrega(formAtribuirPedido.getNomeResponsavel());
                pedido.setSituacao(SituacaoPedido.EM_TRANSPORTE);
            }
        );
        pedidoRepository.saveAll(pedidos);
    }

    @Override
    public void adicionarPedidoNoDeposito(Long pedidoId, Long depositoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId).get();
        pedido.setSituacao(SituacaoPedido.PEDIDO_RECEBIDO);
        pedido.setDepositoId(depositoId);
        pedidoRepository.save(pedido);
    }

    @Override
    public Page<PedidoSimplificadoDTO> buscarPedidosDoCliente(
        Long clienteId,
        SituacaoPedido situacao,
        Pageable pageable
    ) {
        Page<Pedido> pedidos;
        if (situacao != null) {
            pedidos = pedidoRepository.findAllByClienteIdAndSituacao(clienteId, situacao, pageable);
        } else {
            pedidos = pedidoRepository.findAllByClienteId(clienteId, pageable);
        }
        return pedidos.map(PedidoMapper.INSTANCE::converteParaPedidoSimplificadoDTO);
    }

    @Override
    public PedidoDTO buscarPedidoPorCodigo(String codigo) {
        Pedido pedido = pedidoRepository.findByCodigo(codigo).get();
        return PedidoMapper.INSTANCE.converteParaPedidoDTO(pedido);
    }

    @Override
    public Pedido criar(FormCriarPedidoDTO formCriarPedido) {
        Pedido pedido = Pedido
            .builder()
            .clienteId(formCriarPedido.getClienteId())
            .dataEntregaPrevista(calcularDataEntregaPrevista(formCriarPedido.getPrazoDiasUteis()))
            .custoFrete(formCriarPedido.getCustoFrete())
            .nomeDestinatario(formCriarPedido.getNomeDestinatario())
            .documentoDestinatario(formCriarPedido.getDocumentoDestinatario())
            .enderecoDestinatario(
                EnderecoMapper.INSTANCE.converteParaEndereco(formCriarPedido.getEnderecoDestinatario())
            )
            .nomeRemetente(formCriarPedido.getNomeRemetente())
            .enderecoRemetente(EnderecoMapper.INSTANCE.converteParaEndereco(formCriarPedido.getEnderecoRemetente()))
            .build();
        formCriarPedido
            .getVolumes()
            .forEach(
                formVolume -> {
                    pedido.addVolume(VolumeMapper.INSTANCE.converteParaVolume(formVolume));
                }
            );
        pedidoRepository.save(pedido);
        middlewareSGEProducer.notificarCriacaoDoPedido(new MessagePedidoCriadoDTO(pedido.getCodigo()));
        return pedido;
    }

    private LocalDate calcularDataEntregaPrevista(Integer prazoDiasUteis) {
        LocalDate hoje = LocalDate.now();
        for (int i = prazoDiasUteis; i != 0; i--) {
            hoje = hoje.plusDays(1);
            if (DayOfWeek.SUNDAY.equals(hoje.getDayOfWeek())) {
                hoje = hoje.plusDays(1);
            }
        }
        return hoje;
    }
}
