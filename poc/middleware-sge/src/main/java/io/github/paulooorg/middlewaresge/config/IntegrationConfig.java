package io.github.paulooorg.middlewaresge.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.paulooorg.middlewaresge.integration.dto.request.FormCalcularValorSeguroDTO;
import io.github.paulooorg.middlewaresge.integration.dto.request.FormPedidoCriadoDTO;
import io.github.paulooorg.middlewaresge.integration.dto.request.FormPedidoEntregueDTO;
import io.github.paulooorg.middlewaresge.integration.dto.response.CustoSeguroDTO;
import java.nio.charset.StandardCharsets;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.http.dsl.Http;
import org.springframework.integration.jdbc.JdbcMessageHandler;
import org.springframework.messaging.MessageHandler;

@Configuration
@EnableIntegration
@Slf4j
public class IntegrationConfig {

    @Value("${message-broker.pedido-criado.queue}")
    private String pedidoCriadoQueue;

    @Value("${message-broker.pedido-entregue.queue}")
    private String pedidoEntregueQueue;

    private final ObjectMapper objectMapper;

    private final DataSource dataSource;

    @Autowired
    public IntegrationConfig(ObjectMapper objectMapper, DataSource dataSource) {
        this.objectMapper = objectMapper;
        this.dataSource = dataSource;
    }

    @Bean
    public IntegrationFlow calcularSeguroInbound() {
        return IntegrationFlows
            .from(
                Http
                    .inboundGateway("/seguros/calcular")
                    .requestMapping(m -> m.methods(HttpMethod.POST))
                    .requestPayloadType(FormCalcularValorSeguroDTO.class)
            )
            .channel("calcularSeguro.httpRequest")
            .get();
    }

    @Bean
    public IntegrationFlow calcularSeguroOutbound() {
        return IntegrationFlows
            .from("calcularSeguro.httpRequest")
            .handle(
                Http
                    .outboundGateway("http://localhost:9090/sge/seguros/calcular")
                    .httpMethod(HttpMethod.POST)
                    .expectedResponseType(CustoSeguroDTO.class)
                    .mappedRequestHeaders("")
            )
            .get();
    }

    @Bean
    public IntegrationFlow pedidoCriadoInbound(ConnectionFactory connectionFactory) {
        return IntegrationFlows
            .from(Amqp.inboundAdapter(connectionFactory, pedidoCriadoQueue))
            .handle(pedidoCriadoMessageHandler())
            .get();
    }

    @Bean
    public MessageHandler pedidoCriadoMessageHandler() {
        JdbcMessageHandler jdbcMessageHandler = new JdbcMessageHandler(
            dataSource,
            "INSERT INTO pedido_criado (codigo) VALUES(?);"
        );
        jdbcMessageHandler.setPreparedStatementSetter(
            (ps, m) -> {
                String payload = new String((byte[]) m.getPayload());
                try {
                    FormPedidoCriadoDTO formPedidoCriado = objectMapper.readValue(payload, FormPedidoCriadoDTO.class);
                    log.info("Realizando insert na tabela pedido_criado com os dados -> {}", formPedidoCriado);
                    ps.setString(1, formPedidoCriado.getCodigo());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        );
        return jdbcMessageHandler;
    }

    @Bean
    public IntegrationFlow pedidoEntregueInbound(ConnectionFactory connectionFactory) {
        return IntegrationFlows
            .from(Amqp.inboundAdapter(connectionFactory, pedidoEntregueQueue))
            .handle(pedidoEntregueMessageHandler())
            .get();
    }

    @Bean
    public MessageHandler pedidoEntregueMessageHandler() {
        JdbcMessageHandler jdbcMessageHandler = new JdbcMessageHandler(
            dataSource,
            "INSERT INTO pedido_entregue (codigo) VALUES(?);"
        );
        jdbcMessageHandler.setPreparedStatementSetter(
            (ps, m) -> {
                String payload = new String((byte[]) m.getPayload());
                try {
                    FormPedidoEntregueDTO formPedidoEntregue = objectMapper.readValue(
                        payload,
                        FormPedidoEntregueDTO.class
                    );
                    log.info("Realizando insert na tabela pedido_entregue com os dados -> {}", formPedidoEntregue);
                    ps.setString(1, formPedidoEntregue.getCodigo());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        );
        return jdbcMessageHandler;
    }
}
