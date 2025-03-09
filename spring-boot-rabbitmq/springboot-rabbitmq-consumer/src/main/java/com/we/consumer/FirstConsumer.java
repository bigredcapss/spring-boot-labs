package com.we.consumer;

import com.we.entity.Merchant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author we
 * @date 2021-07-15 17:10
 **/
@Component
@PropertySource("classpath:wemq.properties")
@RabbitListener(queues = "${com.we.firstqueue}", containerFactory="rabbitListenerContainerFactory")
public class FirstConsumer {

    @RabbitHandler
    public void process(@Payload Merchant merchant){

        System.out.println("First Queue received msg : " + merchant.getName());

    }

}
