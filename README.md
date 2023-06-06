### 限时抢购

#### 特点
- 通过 MyBatis-Plus 提供的 @Version 乐观锁在更新库存时保证线程安全
- 利用消息队列 Kafka 来缓冲瞬时流量，将同步的下单操作直接调用转成异步的间接推送
- 使用 COLA 提供的组件提供的 Response 和 Exception 简化开发

#### 环境
~~~shell
docker pull wurstmeister/zookeeper  
docker pull wurstmeister/kafka
docker run -d --name zookeeper -p 2181:2181 -t wurstmeister/zookeeper
docker run -d --name kafka -p 9092:9092 -e KAFKA_BROKER_ID=0 \
        -e KAFKA_ZOOKEEPER_CONNECT=115.157.195.198:2181 \
        -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://115.157.195.198:9092 \
        -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 -t wurstmeister/kafka
~~~

#### 数据库建表
~~~sql
CREATE TABLE `stock` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
    `count` int(11) NOT NULL COMMENT '库存',
    `sale` int(11) NOT NULL COMMENT '已售',
    `version` int(11) NOT NULL COMMENT '乐观锁，版本号',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
CREATE TABLE `stock_order` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `sid` int(11) NOT NULL COMMENT '库存ID',
    `name` varchar(30) NOT NULL DEFAULT '' COMMENT '商品名称',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
~~~