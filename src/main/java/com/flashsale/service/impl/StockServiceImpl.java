package com.flashsale.service.impl;

import com.alibaba.cola.exception.BizException;
import com.flashsale.mapper.StockMapper;
import com.flashsale.pojo.Stock;
import com.flashsale.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StockServiceImpl implements StockService {

    @Autowired
    StockMapper stockMapper;


    @Override
    public Stock checkStock(Integer sid) {
        Stock stock = stockMapper.selectById(sid);
        if (stock.getCount() < 1) {
            throw new BizException("STOCK_SALE_ERROR", "库存不足");
        }
        return stock;
    }

    @Override
    public void saleStock(Stock stock) {
        stock.setSale(stock.getSale() + 1);
        stock.setCount(stock.getCount() - 1);
        int res = stockMapper.updateById(stock);
        if (res == 0) {
            throw new BizException("STOCK_SALE_ERROR", "更新库存失败");
        }
    }

}
