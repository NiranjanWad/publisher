package com.publisher.config;

import com.publisher.dao.PublisherService;
import com.publisher.implementation.PublisherServiceImpl;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfig {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Bean
    public TopicExchange eventExchange(){
        return new TopicExchange("eventExchange");
    }


    @Bean
    public PublisherService publisherService(){
        return new PublisherServiceImpl(rabbitTemplate,eventExchange());
    }
}
