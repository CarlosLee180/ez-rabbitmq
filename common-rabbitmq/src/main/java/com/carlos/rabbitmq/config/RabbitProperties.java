package com.carlos.rabbitmq.config;

import com.carlos.rabbitmq.domain.DeclareConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: lje
 * @Date: 2024/8/29 16:07
 */
@Component("myRabbitProperties")
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitProperties {

    private List<DeclareConfig> declareConfig;

    public List<DeclareConfig> getDeclareConfig() {
        return declareConfig;
    }

    public void setDeclareConfig(List<DeclareConfig> declareConfig) {
        this.declareConfig = declareConfig;
    }

}
