package com.we.springbootshardingjdbc.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
 * @author BigRedCaps
 * @date 2021/1/26 21:21
 */
@Data
@Entity
@Table(name = "t_order")
public class OrderEntity implements Serializable
{
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Integer userId;

    private Integer status = 1;
}
