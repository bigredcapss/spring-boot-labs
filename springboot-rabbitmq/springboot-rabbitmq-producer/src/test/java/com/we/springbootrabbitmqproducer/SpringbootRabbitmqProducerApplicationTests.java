package com.we.springbootrabbitmqproducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.we.producer.RabbitMqSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqProducerApplicationTests {

    @Autowired
    RabbitMqSender rabbitMqSender;

    @Test
    public void contextLoads() throws JsonProcessingException {
        // 先启动消费者 consumer，否则交换机、队列、绑定都不存在
        rabbitMqSender.send();
    }

}
