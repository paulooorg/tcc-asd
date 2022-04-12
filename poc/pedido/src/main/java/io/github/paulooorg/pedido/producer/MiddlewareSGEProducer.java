package io.github.paulooorg.pedido.producer;

import io.github.paulooorg.pedido.producer.dto.MessagePedidoCriadoDTO;
import io.github.paulooorg.pedido.producer.dto.MessagePedidoEntregueDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MiddlewareSGEProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${message-broker.pedido-criado.queue}")
    private String pedidoCriadoQueue;

    @Value("${message-broker.pedido-entregue.queue}")
    private String pedidoEntregueQueue;

    @Autowired
    public MiddlewareSGEProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void notificarCriacaoDoPedido(MessagePedidoCriadoDTO messagePedidoCriado) {
        rabbitTemplate.convertAndSend(pedidoCriadoQueue, messagePedidoCriado);
    }

    public void notificarEntregaDoPedido(MessagePedidoEntregueDTO messagePedidoEntregue) {
        rabbitTemplate.convertAndSend(pedidoEntregueQueue, messagePedidoEntregue);
    }
}
