package io.github.paulooorg.cliente.service.impl;

import io.github.paulooorg.cliente.controller.dto.request.FormCalcularFreteDTO;
import io.github.paulooorg.cliente.controller.dto.request.FormCriarPedidoDTO;
import io.github.paulooorg.cliente.controller.dto.response.CustoFreteDTO;
import io.github.paulooorg.cliente.controller.dto.response.EnderecoDTO;
import io.github.paulooorg.cliente.entity.Cliente;
import io.github.paulooorg.cliente.entity.Desconto;
import io.github.paulooorg.cliente.entity.Endereco;
import io.github.paulooorg.cliente.integration.FreteClient;
import io.github.paulooorg.cliente.mapper.EnderecoMapper;
import io.github.paulooorg.cliente.mapper.VolumeMapper;
import io.github.paulooorg.cliente.producer.PedidoProducer;
import io.github.paulooorg.cliente.producer.dto.MessageCriarPedidoDTO;
import io.github.paulooorg.cliente.repository.ClienteRepository;
import io.github.paulooorg.cliente.repository.EnderecoRepository;
import io.github.paulooorg.cliente.service.ClienteService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final FreteClient freteClient;

    private final PedidoProducer pedidoProducer;

    private final EnderecoRepository enderecoRepository;

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(
        FreteClient freteClient,
        PedidoProducer pedidoProducer,
        EnderecoRepository enderecoRepository,
        ClienteRepository clienteRepository
    ) {
        this.freteClient = freteClient;
        this.pedidoProducer = pedidoProducer;
        this.enderecoRepository = enderecoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public CustoFreteDTO calcularFrete(Long clienteId, FormCalcularFreteDTO formCalcularFrete) {
        Cliente cliente = clienteRepository.findById(clienteId).get();
        return calcularFrete(cliente, formCalcularFrete);
    }

    private CustoFreteDTO calcularFrete(Cliente cliente, FormCalcularFreteDTO formCalcularFrete) {
        CustoFreteDTO custoFrete = freteClient.calcular(formCalcularFrete);
        if (cliente.getDesconto() != null) {
            Desconto desconto = cliente.getDesconto();
            BigDecimal valorDesconto = custoFrete
                .getCustoTotal()
                .multiply(desconto.getValor())
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_EVEN);
            custoFrete.setCustoTotal(custoFrete.getCustoTotal().subtract(valorDesconto));
            custoFrete.setDescontoCliente(valorDesconto);
        }
        return custoFrete;
    }

    @Override
    public void criarPedido(Long clienteId, FormCriarPedidoDTO formCriarPedido) {
        Endereco endereco = enderecoRepository.findById(formCriarPedido.getEnderecoId()).get();
        Cliente cliente = clienteRepository.findById(clienteId).get();
        CustoFreteDTO custoFrete = calcularFrete(cliente, formCriarPedido, endereco);
        MessageCriarPedidoDTO messageCriarPedido = criarMessagePedido(cliente, endereco, custoFrete, formCriarPedido);
        pedidoProducer.criarPedido(messageCriarPedido);
    }

    private MessageCriarPedidoDTO criarMessagePedido(
        Cliente cliente,
        Endereco endereco,
        CustoFreteDTO custoFrete,
        FormCriarPedidoDTO formCriarPedido
    ) {
        return MessageCriarPedidoDTO
            .builder()
            .clienteId(cliente.getId())
            .prazoDiasUteis(custoFrete.getPrazoDiasUteis())
            .custoFrete(custoFrete.getCustoTotal())
            .nomeDestinatario(formCriarPedido.getNomeDestinatario())
            .documentoDestinatario(formCriarPedido.getDocumentoDestinatario())
            .enderecoDestinatario(
                EnderecoMapper.INSTANCE.converteParaMessageEnderecoDTO(formCriarPedido.getEnderecoDestinatario())
            )
            .volumes(
                formCriarPedido
                    .getVolumes()
                    .stream()
                    .map(VolumeMapper.INSTANCE::converteParaMessageVolumeDTO)
                    .collect(Collectors.toList())
            )
            .nomeRemetente(cliente.getNome())
            .enderecoRemetente(EnderecoMapper.INSTANCE.converteParaMessageEnderecoDTO(endereco))
            .build();
    }

    private CustoFreteDTO calcularFrete(Cliente cliente, FormCriarPedidoDTO formCriarPedido, Endereco endereco) {
        FormCalcularFreteDTO formCalcularFrete = FormCalcularFreteDTO
            .builder()
            .cepOrigem(endereco.getCep())
            .cepDestino(endereco.getCep())
            .volumes(formCriarPedido.getVolumes())
            .servicosOpcionais(formCriarPedido.getServicosOpcionais())
            .build();
        return calcularFrete(cliente, formCalcularFrete);
    }

    @Override
    public List<EnderecoDTO> buscarEnderecosDoCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).get();
        return cliente
            .getEnderecos()
            .stream()
            .map(EnderecoMapper.INSTANCE::converteParaEnderecoDTO)
            .collect(Collectors.toList());
    }
}
