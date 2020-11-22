package com.example.demo.consumers.language.ru;

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
        "com.example.demo.consumers.language.ru",
        "com.example.demo.models",
        "com.example.demo.utils"
})
public class RussianLanguageExchangeConsumer implements CommandLineRunner {
    private static final String RUSSIAN_QUEUE_NAME = "russian_queue";
    private static final String EXCHANGE_NAME = "ru_docs";

    @Autowired private ObjectMapper objectMapper;

    public static void main(String[] args) {
        new SpringApplicationBuilder(RussianLanguageExchangeConsumer.class)
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
        exchangeChannel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        channel.basicConsume(RUSSIAN_QUEUE_NAME, false, (tag, message) -> {
            Statement statement = objectMapper.readValue(message.getBody(), Statement.class);
            exchangeChannel.basicPublish(EXCHANGE_NAME, "", null, objectMapper.writeValueAsBytes(statement));
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        }, tag -> {
        });
    }
}