package com.example.admin.service;

import com.example.admin.model.StaffDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void sendStaffDetails(StaffDto staffDto){

        rabbitTemplate.convertAndSend(exchange,routingKey,staffDto);
    }
}
