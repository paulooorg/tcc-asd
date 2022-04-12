package io.github.paulooorg.entrega.producer;

import io.github.paulooorg.entrega.producer.dto.MessageEntregarPedidoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PedidoProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${message-broker.entregar-pedido.queue}")
    private String entregarPedidoQueue;

    @Autowired
    public PedidoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void entregarPedido(MessageEntregarPedidoDTO messageEntregarPedido) {
        rabbitTemplate.convertAndSend(entregarPedidoQueue, messageEntregarPedido);
    }
}
