# RabbitMQ 公共模块说明
## 模块说明
该模块提取rabbit mq中间件服务为公共能力，封装了spring-boot-starter-amqp服务
需要引入rabbit mq依赖的模块 引入此模块即可
该模块摒弃了复杂冗余配置，不再使用反射对spring官方封装的依赖进行底层修改
只保留了最基础的通过配置文件完成交换器、队列的创建，以及完成交换器-队列的绑定关系，有助于在迁移系统时减少繁杂重复的工作
## <mark>引入步骤
1. 修改需要引入rabbitmq依赖模块（下称“业务模块”）的pom.xml
```
<!-- Common RabbitMq -->
<dependency>
    <groupId>com.carlos</groupId>
    <artifactId>common-rabbitmq</artifactId>
</dependency>
```
2. 修改配置文件，增加rabbitmq相关配置

```
spring:
  rabbitmq:
    # cluster,single
    # mode: single
    host: ip地址
    port: 端口号
    username: 用户名
    password: 密码
    #虚拟host 可以不设置,使用server默认host
    virtual-host: /
    # 声明交换器、队列、路由键
    declareConfig:
      - exchange: 交换器名称，如dataReportExchange
        #交换机类型：可以不配置，默认为topic
        # fanout ：把所有发送到该交换器的消息路由到所有与该交换器绑定的队列中。不需要指定Routingkey和BindingKey
        # direct ：把消息路由到Routingkey与BindingKey完全匹配的队列中。一个交换器可以与多个队列绑定，同时一个交换器与一个队列绑定的时候可以使用多个BindingKey来多次绑定。
        # topic  ：把消息路由到Routingkey与BindingKey匹配的队列中,但它不是完全匹配，而是模糊匹配
        # header ：交换器不依赖于路由键的匹配规则来路由消息，而是根据发送的消息内容中的headers属性进行匹配
        type: topic
        # 声明队列和路由键
        # 每一个-对应一个队列和路由键并自动进行绑定
        bindList:
          - queue: test_common_report
            routingKey: test.common.report
```
3. 修改业务模块的启动类，增加@EnableRabbit注解，修改@SpringBootApplication注解，增加扫包路径，确保rabbit初始化队列在启动时执行

```
@EnableRabbit
@SpringBootApplication(scanBasePackages = {"com.carlos.rabbitmq"})
```
