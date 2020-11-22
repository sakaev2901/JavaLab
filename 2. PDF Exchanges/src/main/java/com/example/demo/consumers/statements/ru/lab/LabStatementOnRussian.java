package com.example.demo.consumers.statements.ru.lab;

import com.example.demo.models.Statement;
import com.example.demo.utils.PdfGenerator;
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
        "com.example.demo.consumers.statements.ru.lab",
        "com.example.demo.models",
        "com.example.demo.utils",
        "com.example.demo.config"
})
public class LabStatementOnRussian  implements CommandLineRunner {
    private static final String EXCHANGE_NAME = "ru_docs";

    @Autowired private PdfGenerator pdfGenerator;
    @Autowired private ObjectMapper objectMapper;


    public static void main(String[] args) {
        new SpringApplicationBuilder(LabStatementOnRussian.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, EXCHANGE_NAME, "");
        channel.basicConsume(queue, false, (tag, message) -> {
            Statement statement = objectMapper.readValue(message.getBody(), Statement.class);
            pdfGenerator.generatePdfDocs(statement, "lab", "ru");
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        }, tag -> {});
    }
}
