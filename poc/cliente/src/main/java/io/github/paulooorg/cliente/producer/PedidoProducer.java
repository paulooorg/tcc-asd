package io.github.paulooorg.cliente.producer;

import io.github.paulooorg.cliente.producer.dto.MessageCriarPedidoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PedidoProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${message-broker.criar-pedido.queue}")
    private String criarPedidoQueue;

    @Autowired
    public PedidoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void criarPedido(MessageCriarPedidoDTO messageCriarPedido) {
        rabbitTemplate.convertAndSend(criarPedidoQueue, messageCriarPedido);
    }
}
