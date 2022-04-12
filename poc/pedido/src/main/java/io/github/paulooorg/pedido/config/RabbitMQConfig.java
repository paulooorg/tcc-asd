package io.github.paulooorg.pedido.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${message-broker.criar-pedido.queue}")
    private String criarPedidoQueue;

    @Value("${message-broker.criar-pedido.exchange}")
    private String criarPedidoExchange;

    @Value("${message-broker.criar-pedido.routing-key}")
    private String criarPedidoRoutingKey;

    @Value("${message-broker.atribuir-pedido.queue}")
    private String atribuirPedidoQueue;

    @Value("${message-broker.atribuir-pedido.exchange}")
    private String atribuirPedidoExchange;

    @Value("${message-broker.atribuir-pedido.routing-key}")
    private String atribuirPedidoRoutingKey;

    @Value("${message-broker.entregar-pedido.queue}")
    private String entregarPedidoQueue;

    @Value("${message-broker.entregar-pedido.exchange}")
    private String entregarPedidoExchange;

    @Value("${message-broker.entregar-pedido.routing-key}")
    private String entregarPedidoRoutingKey;

    @Bean
    public Queue criarPedidoQueue() {
        return new Queue(criarPedidoQueue, true);
    }

    @Bean
    public DirectExchange criarPedidoExchange() {
        return new DirectExchange(criarPedidoExchange);
    }

    @Bean
    public Binding criarPedidoBinding() {
        return BindingBuilder.bind(criarPedidoQueue()).to(criarPedidoExchange()).with(criarPedidoRoutingKey);
    }

    @Bean
    public Queue atribuirPedidoQueue() {
        return new Queue(atribuirPedidoQueue, true);
    }

    @Bean
    public DirectExchange atribuirPedidoExchange() {
        return new DirectExchange(atribuirPedidoExchange);
    }

    @Bean
    public Binding atribuirPedidoBinding() {
        return BindingBuilder.bind(atribuirPedidoQueue()).to(atribuirPedidoExchange()).with(atribuirPedidoRoutingKey);
    }

    @Bean
    public Queue entregarPedidoQueue() {
        return new Queue(entregarPedidoQueue, true);
    }

    @Bean
    public DirectExchange entregarPedidoExchange() {
        return new DirectExchange(entregarPedidoExchange);
    }

    @Bean
    public Binding entregarPedidoBinding() {
        return BindingBuilder.bind(entregarPedidoQueue()).to(entregarPedidoExchange()).with(entregarPedidoRoutingKey);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
