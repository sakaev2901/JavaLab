package com.example.demo.services;

import com.example.demo.dtos.UserDto;
import com.example.demo.models.Statement;
import com.example.demo.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Service
public class DocsServiceImpl implements DocsService {
    private static final String LANGUAGE_EXCHANGE_NAME = "docs_lang_direct_ex";
    private static final String TATAR_QUEUE_BINDING_KEY = "tatar";
    private static final String RUSSIAN_QUEUE_BINDING_KEY = "russian";
    private static final String TATAR_QUEUE_NAME = "tatar_queue";
    private static final String RUSSIAN_QUEUE_NAME = "russian_queue";

    @Autowired private ObjectMapper objectMapper;
    private Channel channel;


    @Override
    public void sendToExchange(UserDto userDto) {
        try {
           tryToSend(userDto);
        } catch (IOException | TimeoutException e) {
            throw new IllegalStateException(e);
        }
    }

    private void tryToSend(UserDto userDto) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection;
        connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(LANGUAGE_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.queueBind(TATAR_QUEUE_NAME, LANGUAGE_EXCHANGE_NAME, TATAR_QUEUE_BINDING_KEY);
        channel.queueBind(RUSSIAN_QUEUE_NAME, LANGUAGE_EXCHANGE_NAME, RUSSIAN_QUEUE_BINDING_KEY);
        Statement statement = Statement.builder()
                .date(new Date())
                .user(User.from(userDto))
                .id(UUID.randomUUID().toString())
                .build();
        switch (userDto.getLanguage()) {
            case "tat": {
                channel.basicPublish(LANGUAGE_EXCHANGE_NAME, TATAR_QUEUE_BINDING_KEY, null, objectMapper.writeValueAsBytes(statement));
            } break;
            case "ru": {
                channel.basicPublish(LANGUAGE_EXCHANGE_NAME, RUSSIAN_QUEUE_BINDING_KEY, null, objectMapper.writeValueAsBytes(statement));
            }
        }
    }

    public void throwA() throws IOException {
        throw new IOException();
    }
}
