package com.publisher.implementation;

import com.publisher.dao.PublisherService;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PublisherServiceImpl implements PublisherService {

    private final RabbitTemplate rabbitTemplate;

    private final TopicExchange exchange;

    @Autowired
    public PublisherServiceImpl(RabbitTemplate rabbitTemplate, TopicExchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void sendMessage(){
        String routingKey = "sub.test.first";
        String message = "Sending my first message";
        rabbitTemplate.convertAndSend(exchange.getName(),routingKey,message);
    }
}
