package com.flashsale.controller;


import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.fastjson.JSON;
import com.flashsale.pojo.Stock;
import com.flashsale.service.StockOrderService;
import com.flashsale.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    StockService stockService;

    @Autowired
    StockOrderService stockOrderService;

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 限时抢购
     *
     * @return Ajax Type Response
     */
    @PostMapping("/{sid}")
    public SingleResponse<String> reduceStock(@PathVariable Integer sid) {
        // 数据库校验库存
        Stock stock = stockService.checkStock(sid);
        // 扣库存（乐观锁）
        stockService.saleStock(stock);
        // 生成订单
        stockOrderService.createOrder(stock);
        return SingleResponse.of("下单成功");
    }


    @PostMapping("/kafka/{sid}")
    public SingleResponse<String>  reduceStockWithKafka(@PathVariable Integer sid) {
        // 数据库校验库存
        Stock stock = stockService.checkStock(sid);
        // 序列化后发送到 Kafka
        kafkaTemplate.send("FLASH_SALE_TOPIC", JSON.toJSONString(stock));
        return SingleResponse.of("抢购请求正在处理中...");
    }

}
