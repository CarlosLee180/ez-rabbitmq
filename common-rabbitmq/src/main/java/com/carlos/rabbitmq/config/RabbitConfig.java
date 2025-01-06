package com.carlos.rabbitmq.config;

import com.carlos.rabbitmq.domain.BindConfig;
import com.carlos.rabbitmq.domain.DeclareConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: lje
 * @Date: 2024/8/29 14:50
 */
@Configuration
public class RabbitConfig {

    private static final Logger log = LoggerFactory.getLogger(RabbitConfig.class);

    public static final String TOPIC_QUEUE_TO_SCREEN = "send_to_screen";

    public static final String TOPIC_QUEUE_TO_GAODE = "send_to_gaode";

    @Resource
    private AmqpAdmin amqpAdmin;

    @Resource
    private RabbitProperties rabbitProperties;

    @PostConstruct
    public void init() {
        // 获取配置文件
        List<DeclareConfig> declareConfig = rabbitProperties.getDeclareConfig();
        log.info("初始化队列与交换机：{}", declareConfig);
        declareConfig.forEach(exchane -> {

            amqpAdmin.declareExchange(new TopicExchange(exchane.getExchange(), true, false));
            List<BindConfig> bindList = exchane.getBindList();
            bindList.forEach(queue -> {
                amqpAdmin.declareQueue(new Queue(queue.getQueue(), true));
                amqpAdmin.declareBinding(new Binding(queue.getQueue(), Binding.DestinationType.QUEUE,
                        exchane.getExchange(), queue.getRoutingKey(), null));
            });

        });

    }


}
