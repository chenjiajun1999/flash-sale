package com.flashsale.consumer;

import com.alibaba.cola.exception.BizException;
import com.alibaba.fastjson.JSON;
import com.flashsale.pojo.Stock;
import com.flashsale.service.StockOrderService;
import com.flashsale.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class StockConsumer {

    @Autowired
    StockService stockService;

    @Autowired
    StockOrderService stockOrderService;

    /**
     * MANUAL_IMMEDIATE: 正常场景下会执行 ack 操作，提交 offset 到 kafka 服务器
     * 异常处理交给 Handler 会出现 Kafka ack 操作没有执行导致重复消费
     */
    @KafkaListener(topics = "FLASH_SALE_TOPIC")
    public void listen(ConsumerRecord<String, String> record, Acknowledgment ack) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        String message = (String) kafkaMessage.get();
        ack.acknowledge();
        Stock stock = JSON.parseObject(message, Stock.class);
        try {
            stockService.saleStock(stock);
            stockOrderService.createOrder(stock);
        } catch (BizException e) {
            log.info("下单失败");
        }
    }
}