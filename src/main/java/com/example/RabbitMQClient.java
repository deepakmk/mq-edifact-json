package com.example;

import com.rabbitmq.client.*;

public class RabbitMQClient {

    private final String queueName;
    private final String host;
    private final String user;
    private final String password;
    private final String port;

    public RabbitMQClient() {
        this.host = ConfigLoader.getProperty("rabbitmq.host");
        this.user = ConfigLoader.getProperty("rabbitmq.user");
        this.password = ConfigLoader.getProperty("rabbitmq.password");
        this.port = ConfigLoader.getProperty("rabbitmq.port");
        this.queueName = ConfigLoader.getProperty("rabbitmq.queue");
    }

    public void sendMessage(String message) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(Integer.parseInt(port));
        factory.setUsername(user);
        factory.setPassword(password);
        try (Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()) {

            channel.queueDeclare(queueName, false, false, false, null);
            channel.basicPublish("", queueName, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "' to queue '" + queueName + "'");
        }
    }

    public String receiveMessage(String queueName) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(Integer.parseInt(port));
        factory.setUsername(user);
        factory.setPassword(password);
        try (Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()) {
            channel.queueDeclare(queueName, false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            };
            return channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });

        }

    }
}
