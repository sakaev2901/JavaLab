package com.example.demo.consumer.app2;

import com.example.demo.consumer.app3.FireApp;
import com.example.demo.consumer.dtos.UserDto;
import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
@ComponentScan("com.example.demo.consumer.app2")
public class ExpellingApp implements CommandLineRunner {

    private final static String EXCHANGE_NAME = "documents";

    @Autowired
    private ExpellingPdfGenerator expellingPdfGenerator;

    public static void main(String[] args) {
        SpringApplication.run(ExpellingApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, EXCHANGE_NAME, "");
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                UserDto userDto = new UserDto(new String(message.getBody()));
                System.out.println(userDto);
                expellingPdfGenerator.generate(userDto);
            };
            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalStateException(e);
        }


    }
}
