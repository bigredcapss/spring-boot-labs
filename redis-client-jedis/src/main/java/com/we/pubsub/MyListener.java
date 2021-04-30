package com.we.pubsub;

import redis.clients.jedis.JedisPubSub;

/**
 * @author we
 * @date 2021-04-30 14:06
 **/
public class MyListener extends JedisPubSub {
    /**
     * 取得订阅消息后的处理
     * @param channel
     * @param message
     */
    @Override
    public void onMessage(String channel, String message) {
        super.onMessage(channel, message);
        System.out.println(channel + " = " + message);
    }

    /**
     * 取得按表达式的方式订阅的消息后的处理
     * @param pattern
     * @param channel
     * @param message
     */
    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println(pattern + " = " + channel + " = " + message);
    }

    /**
     * 初始化订阅时候的处理
     * @param channel
     * @param subscribedChannels
     */
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        // System.out.println(channel + " = " + subscribedChannels);
    }

    /**
     * 取消订阅时候的处理
     * @param channel
     * @param subscribedChannels
     */
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        // System.out.println(channel + " = " + subscribedChannels);
    }

    /**
     * 取消按表达式的方式订阅时候的处理
     * @param pattern
     * @param subscribedChannels
     */
    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        // System.out.println(pattern + " = " + subscribedChannels);
    }

    /**
     * 初始化按表达式的方式订阅时候的处理
     * @param pattern
     * @param subscribedChannels
     */
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        // System.out.println(pattern + " = " + subscribedChannels);

    }
}
