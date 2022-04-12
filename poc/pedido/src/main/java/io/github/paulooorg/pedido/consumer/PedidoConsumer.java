package io.github.paulooorg.pedido.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {

    private final PedidoProcessor pedidoProcessor;

    @Autowired
    public PedidoConsumer(PedidoProcessor pedidoProcessor) {
        this.pedidoProcessor = pedidoProcessor;
    }

    @RabbitListener(queues = "${message-broker.criar-pedido.queue}")
    public void criarPedido(@Payload String message) {
        pedidoProcessor.criarPedido(message);
    }

    @RabbitListener(queues = "${message-broker.atribuir-pedido.queue}")
    public void atribuirPedido(@Payload String message) {
        pedidoProcessor.atribuirPedido(message);
    }

    @RabbitListener(queues = "${message-broker.entregar-pedido.queue}")
    public void entregarPedido(@Payload String message) {
        pedidoProcessor.entregarPedido(message);
    }
}
