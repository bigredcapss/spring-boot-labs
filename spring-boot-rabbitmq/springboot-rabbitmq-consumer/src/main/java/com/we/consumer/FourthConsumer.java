package com.we.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author we
 * @date 2021-07-15 17:11
 **/
@Component
@PropertySource("classpath:wemq.properties")
@RabbitListener(queues = "${com.we.fourthqueue}", containerFactory="rabbitListenerContainerFactory")
public class FourthConsumer {
    @RabbitHandler
    public void process(String message) throws IOException {

        System.out.println("Fourth Queue received msg : " + message);
    }
}
