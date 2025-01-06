package com.carlos.rabbitmq.domain;

/**
 * @Author: lje
 * @Date: 2024/8/29 14:48
 */
public class BindConfig {

    private String queue;
    private String routingKey;

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    @Override
    public String toString() {
        return "BindConfig{" +
                "queue='" + queue + '\'' +
                ", routingKey='" + routingKey + '\'' +
                '}';
    }
}
