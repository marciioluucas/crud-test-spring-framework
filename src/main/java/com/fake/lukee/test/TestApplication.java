package com.fake.lukee.test;

import com.fake.lukee.test.util.RabbitMQ;

import com.rabbitmq.client.DeliverCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.nio.charset.StandardCharsets;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class TestApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestApplication.class, args);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Node -> '" + message + "' - ConsumerTag '" + consumerTag + "'");
        };

        new RabbitMQ().queue("message").registerCallback(deliverCallback).start();
    }

}
