package com.carlos.rabbitmq.domain;

import java.util.List;

/**
 * @Author: lje
 * @Date: 2024/8/29 14:43
 */
public class DeclareConfig {

    private String exchange;
    private String type = "topic";
    private List<BindConfig> bindList;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public List<BindConfig> getBindList() {
        return bindList;
    }

    public void setBindList(List<BindConfig> bindList) {
        this.bindList = bindList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DeclareConfig{" +
                "exchange='" + exchange + '\'' +
                ", type='" + type + '\'' +
                ", bindList=" + bindList +
                '}';
    }
}
