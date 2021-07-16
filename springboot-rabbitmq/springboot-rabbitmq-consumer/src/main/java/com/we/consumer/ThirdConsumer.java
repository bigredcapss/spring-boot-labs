package com.we.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author we
 * @date 2021-07-15 17:11
 **/
@Component
@PropertySource("classpath:wemq.properties")
@RabbitListener(queues = "${com.we.thirdqueue}", containerFactory="rabbitListenerContainerFactory")
public class ThirdConsumer {
    @RabbitHandler
    public void process(String msg){
        System.out.println("Third Queue received msg : " + msg);
    }
}
