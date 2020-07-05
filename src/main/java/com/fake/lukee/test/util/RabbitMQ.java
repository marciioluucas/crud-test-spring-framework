package com.fake.lukee.test.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;


public class RabbitMQ {

    private String queue;

    private String host;

    private Integer port;

    private DeliverCallback deliverCallback;

    ConnectionFactory factory = new ConnectionFactory();


    public RabbitMQ() {
        queue = "message";
        host = "localhost";
    }


    public void start() throws Exception {

        if ("".equals(queue)) throw new Exception("Por favor, informe a queue");
        if (port != null) factory.setPort(port);

        factory.setHost(host);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(queue, false, false, false, null);

        if (deliverCallback != null)
            channel.basicConsume(queue, true, deliverCallback, consumerTag -> {});

        System.out.println(" [*] Esperando as mensagens do Node");


    }

    public RabbitMQ queue(String queue) throws Exception {
        this.queue = queue;
        return this;
    }


    public RabbitMQ registerCallback(DeliverCallback callback) {
        deliverCallback = callback;
        return this;
    }
}
