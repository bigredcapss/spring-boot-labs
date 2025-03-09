package com.we.springbootshardingjdbc.service;

import com.we.springbootshardingjdbc.entity.OrderEntity;
import com.we.springbootshardingjdbc.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author BigRedCaps
 * @date 2021/1/26 21:20
 */
@Service
public class OrderService {

    @Resource
    private OrderRepository orderRepository;

    public void save(OrderEntity entity) {
        orderRepository.save(entity);
    }

}
