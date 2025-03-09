package com.we.springbootshardingjdbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * http://shardingsphere.apache.org/index.html
 * https://shardingsphere.apache.org/document/legacy/4.x/document/en/manual/sharding-jdbc/
 * http://shardingsphere.apache.org/index_zh.html
 */

@SpringBootApplication(exclude = JtaAutoConfiguration.class)
public class SpringbootShardingJdbcApplication implements CommandLineRunner
{

    public static void main (String[] args)
    {
        SpringApplication.run(SpringbootShardingJdbcApplication.class, args);
    }

    @Resource
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(dataSource);
    }

}
