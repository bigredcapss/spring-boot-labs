package com.we.transaction;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * 事务的四（五）大命令--multi,exec,discard,watch,unwatch
 * @author we
 * @date 2021-04-30 11:41
 **/
public class TransactionTest {
    public static void main(String[] args) {
        new Thread(){
            public void run(){
                Jedis jedis = new Jedis("172.16.63.69", 6379);
                String watch = jedis.watch("trxkey");
                System.out.println("method1线程["+Thread.currentThread().getName()+"]watch结果："+watch);
                Transaction multi = jedis.multi();
                multi.set("trxkey","0001-thread1");
                // 让Thread2先执行
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<Object> exec = multi.exec();
                System.out.println("method1线程执行结果："+exec);
                jedis.unwatch();
            }
        }.start();

        new Thread(){
            public void run(){
                Jedis jedis = new Jedis("172.16.63.69", 6379);
                String watch = jedis.watch("trxkey");
                System.out.println("method2线程["+Thread.currentThread().getName()+"]watch结果："+watch);
                Transaction multi = jedis.multi();
                multi.set("trxkey","0001-thread2");
                List<Object> exec = multi.exec();
                System.out.println("method2线程执行结果："+exec);
            }
        }.start();

    }
}
