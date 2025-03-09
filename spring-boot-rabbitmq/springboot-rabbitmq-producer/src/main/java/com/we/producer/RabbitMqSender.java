package com.we.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.we.entity.Merchant;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author we
 * @date 2021-07-15 16:47
 **/
@Component
@PropertySource("classpath:wemq.properties")
public class RabbitMqSender {

    @Value("${com.we.directexchange}")
    private String directExchange;

    @Value("${com.we.topicexchange}")
    private String topicExchange;

    @Value("${com.we.fanoutexchange}")
    private String fanoutExchange;

    @Value("${com.we.directroutingkey}")
    private String directRoutingKey;

    @Value("${com.we.topicroutingkey1}")
    private String topicRoutingKey1;

    @Value("${com.we.topicroutingkey2}")
    private String topicRoutingKey2;


    // 自定义的模板，所有的消息都会转换成JSON发送
    @Autowired
    AmqpTemplate rabbitMqTemplate;

    public void send() throws JsonProcessingException {
        Merchant merchant =  new Merchant(1001,"a direct msg : 中原镖局","汉中省解放路266号");
        rabbitMqTemplate.convertAndSend(directExchange,directRoutingKey, merchant);

        rabbitMqTemplate.convertAndSend(topicExchange,topicRoutingKey1, "a topic msg : shanghai.we.teacher");
        rabbitMqTemplate.convertAndSend(topicExchange,topicRoutingKey2, "a topic msg : changsha.we.student");

        // 发送JSON字符串
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(merchant);
        System.out.println(json);
        rabbitMqTemplate.convertAndSend(fanoutExchange,"", json);
    }


}
