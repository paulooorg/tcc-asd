package io.github.paulooorg.parceiro.producer;

import io.github.paulooorg.parceiro.producer.dto.MessageAtribuirPedidoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PedidoProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${message-broker.atribuir-pedido.queue}")
    private String atribuirPedidoQueue;

    @Autowired
    public PedidoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void atribuirPedido(MessageAtribuirPedidoDTO messageAtribuirPedido) {
        rabbitTemplate.convertAndSend(atribuirPedidoQueue, messageAtribuirPedido);
    }
}
