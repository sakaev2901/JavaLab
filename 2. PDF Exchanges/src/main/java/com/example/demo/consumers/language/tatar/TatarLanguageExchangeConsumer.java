package com.example.demo.consumers.language.tatar;

import com.example.demo.models.Statement;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "com.example.demo.consumers.language.tatar",
        "com.example.demo.models",
        "com.example.demo.utils"
})
public class TatarLanguageExchangeConsumer implements CommandLineRunner {
    private static final String TATAR_QUEUE_NAME = "tatar_queue";
    private static final String TAT_EXPELLING_QUEUE_BINDING_KEY = "docs.tat.expelling";
    private static final String TAT_HOSPITAL_QUEUE_BINDING_KEY = "docs.tat.hospital";
    private static final String TAT_LAB_QUEUE_BINDING_KEY = "docs.tat.lab";
    private static final String EXCHANGE_NAME = "tat_docs";

    @Autowired private ObjectMapper objectMapper;

    public static void main(String[] args) {
        new SpringApplicationBuilder(TatarLanguageExchangeConsumer.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        Connection exchangeConnection = connectionFactory.newConnection();
        Channel exchangeChannel = exchangeConnection.createChannel();
        exchangeChannel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        channel.basicConsume(TATAR_QUEUE_NAME, false, (tag, message) -> {
            Statement statement = objectMapper.readValue(message.getBody(), Statement.class);
            exchangeChannel.basicPublish(EXCHANGE_NAME, TAT_LAB_QUEUE_BINDING_KEY, null, objectMapper.writeValueAsBytes(statement));
            exchangeChannel.basicPublish(EXCHANGE_NAME, TAT_EXPELLING_QUEUE_BINDING_KEY, null, objectMapper.writeValueAsBytes(statement));
            exchangeChannel.basicPublish(EXCHANGE_NAME, TAT_HOSPITAL_QUEUE_BINDING_KEY, null, objectMapper.writeValueAsBytes(statement));
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        }, tag -> {
        });
    }
}
