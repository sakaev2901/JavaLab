package com.example.demo.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
@ComponentScan("com.example.demo.producer")
public class Producer implements CommandLineRunner {

    private final static String EXCHANGE = "documents";

    public static void main(String[] args) {
        SpringApplication.run(Producer.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.FANOUT);
            while (true) {
                String message = "";
                System.out.println("ФИО:");
                String fullName = scanner.nextLine();
                System.out.println("Возраст:");
                String age = scanner.nextLine();
                System.out.println("Дата Рождения:");
                String birthday = scanner.nextLine();
                System.out.println("Номер и серия паспорта:");
                String id = scanner.nextLine();
                System.out.println("Выдан:");
                String issued = scanner.nextLine();
                message = fullName + "-" + age + "-" + birthday + "-" + id + "-" + issued;
                channel.basicPublish(EXCHANGE, "", null, message.getBytes());
            }
        } catch (IOException | TimeoutException e) {
            throw new IllegalStateException(e);
        }
    }
}
