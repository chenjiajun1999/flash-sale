package com.flashsale.service;

import com.flashsale.pojo.Stock;

public interface StockService {
    Stock checkStock(Integer sid);

    void saleStock(Stock stock);
}
