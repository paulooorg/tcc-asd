package io.github.paulooorg.middlewaresge.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${message-broker.pedido-entregue.queue}")
    private String pedidoEntregueQueue;

    @Value("${message-broker.pedido-entregue.exchange}")
    private String pedidoEntregueExchange;

    @Value("${message-broker.pedido-entregue.routing-key}")
    private String pedidoEntregueRoutingKey;

    @Value("${message-broker.pedido-criado.queue}")
    private String pedidoCriadoQueue;

    @Value("${message-broker.pedido-criado.exchange}")
    private String pedidoCriadoExchange;

    @Value("${message-broker.pedido-criado.routing-key}")
    private String pedidoCriadoRoutingKey;

    @Bean
    public Queue pedidoEntregueQueue() {
        return new Queue(pedidoEntregueQueue, true);
    }

    @Bean
    public DirectExchange pedidoEntregueExchange() {
        return new DirectExchange(pedidoEntregueExchange);
    }

    @Bean
    public Binding pedidoEntregueBinding() {
        return BindingBuilder.bind(pedidoEntregueQueue()).to(pedidoEntregueExchange()).with(pedidoEntregueRoutingKey);
    }

    @Bean
    public Queue pedidoCriadoQueue() {
        return new Queue(pedidoCriadoQueue, true);
    }

    @Bean
    public DirectExchange pedidoCriadoExchange() {
        return new DirectExchange(pedidoCriadoExchange);
    }

    @Bean
    public Binding pedidoCriadoBinding() {
        return BindingBuilder.bind(pedidoCriadoQueue()).to(pedidoCriadoExchange()).with(pedidoCriadoRoutingKey);
    }
}
