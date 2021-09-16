package com.we.es.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author we
 * @date 2021-09-16 09:38
 **/
@Data
@Document(indexName = "userindex",type = "user")
public class User implements Serializable {

    private Long id;

    private String name;

    private Integer age;

    private String description;

    private String createtime;


}
