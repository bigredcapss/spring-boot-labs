package com.we.springbootshardingjdbc.repository;

import com.we.springbootshardingjdbc.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author BigRedCaps
 * @date 2021/1/26 21:27
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Long>
{
}
