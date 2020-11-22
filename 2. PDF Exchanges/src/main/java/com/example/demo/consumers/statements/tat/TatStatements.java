package com.example.demo.consumers.statements.tat;


import com.example.demo.models.Statement;
import com.example.demo.utils.PdfGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        "com.example.demo.consumers.statements.tat",
        "com.example.demo.models",
        "com.example.demo.utils",
        "com.example.demo.config"
})
public class TatStatements implements CommandLineRunner {
    private static final String LOSS_ROUTING_KEY= "docs.tat.*";
    private static final String DOCS_EXCHANGE = "tat_docs";

    @Autowired private PdfGenerator pdfGenerator;
    @Autowired private ObjectMapper objectMapper;

    public static void main(String[] args) {
        new SpringApplicationBuilder(TatStatements.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, DOCS_EXCHANGE, LOSS_ROUTING_KEY);
        channel.basicConsume(queueName, false, (tag, message) -> {
            Statement statement = objectMapper.readValue(message.getBody(), Statement.class);
            pdfGenerator.generatePdfDocs(statement, message.getEnvelope().getRoutingKey(), "tat");
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false );
        }, tag -> {});
    }
}
