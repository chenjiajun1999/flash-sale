package com.flashsale.service.impl;

import com.alibaba.cola.exception.BizException;
import com.flashsale.mapper.StockOrderMapper;
import com.flashsale.pojo.Stock;
import com.flashsale.pojo.StockOrder;
import com.flashsale.service.StockOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class StockOrderServiceImpl implements StockOrderService {

    @Autowired
    StockOrderMapper stockOrderMapper;

    @Override
    public void createOrder(Stock stock) {
        StockOrder order = new StockOrder();
        order.setSid(stock.getId());
        order.setName(stock.getName());
        order.setCreateTime(new Date());
        int res = stockOrderMapper.insert(order);
        if (res == 0) {
            throw new BizException("STOCK_SALE_ERROR" ,"创建订单失败");
        }
        log.info("下单成功");
    }
}
