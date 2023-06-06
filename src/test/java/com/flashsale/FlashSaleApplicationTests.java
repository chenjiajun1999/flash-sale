package com.flashsale;

import com.flashsale.mapper.StockMapper;
import com.flashsale.pojo.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class FlashSaleApplicationTests {

    @Autowired
    StockMapper stockMapper;

    private final static String TOPIC_NAME = "test-topic";
    @Value("${spring.kafka.consumer.group-id}")
    private final static String GROUP_ID = "test-topic";

    @Test
    void contextLoads() {
    }

    @Test
    void stockMapperInsert() {
        Stock stock = new Stock();
        stock.setCount(50);
        stock.setVersion(0);
        stock.setSale(10);
        stock.setName("苹果手机");
        stockMapper.insert(stock);
    }

}
