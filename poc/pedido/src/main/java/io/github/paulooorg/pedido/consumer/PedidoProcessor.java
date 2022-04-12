package io.github.paulooorg.pedido.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.paulooorg.pedido.consumer.dto.FormAtribuicaoPedidoDTO;
import io.github.paulooorg.pedido.consumer.dto.FormCriarPedidoDTO;
import io.github.paulooorg.pedido.consumer.dto.FormEntregarPedidoDTO;
import io.github.paulooorg.pedido.service.PedidoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PedidoProcessor {

    private final ObjectMapper objectMapper;

    private final PedidoService pedidoService;

    @Autowired
    public PedidoProcessor(ObjectMapper objectMapper, PedidoService pedidoService) {
        this.objectMapper = objectMapper;
        this.pedidoService = pedidoService;
    }

    public void criarPedido(String message) {
        try {
            FormCriarPedidoDTO formCriarPedido = objectMapper.readValue(message, FormCriarPedidoDTO.class);
            log.info("Mensagem recebida em criarPedido(...) -> {}", formCriarPedido);
            pedidoService.criar(formCriarPedido);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void atribuirPedido(String message) {
        try {
            FormAtribuicaoPedidoDTO formAtribuirPedido = objectMapper.readValue(message, FormAtribuicaoPedidoDTO.class);
            log.info("Mensagem recebida em atribuirPedido(...) -> {}", formAtribuirPedido);
            pedidoService.atribuirPedido(formAtribuirPedido);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void entregarPedido(String message) {
        try {
            FormEntregarPedidoDTO formEntregarPedido = objectMapper.readValue(message, FormEntregarPedidoDTO.class);
            log.info("Mensagem recebida em entregarPedido(...) -> {}", formEntregarPedido);
            pedidoService.entregarPedido(formEntregarPedido);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
