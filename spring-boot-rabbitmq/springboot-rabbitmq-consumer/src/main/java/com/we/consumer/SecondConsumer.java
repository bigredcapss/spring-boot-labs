package com.we.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
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
@RabbitListener(queues = "${com.we.secondqueue}", containerFactory="rabbitListenerContainerFactory")
public class SecondConsumer {
    @RabbitHandler
    public void process(String msgContent, Channel channel, Message message) throws IOException {
        System.out.println("Second Queue received msg : " + msgContent );

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
