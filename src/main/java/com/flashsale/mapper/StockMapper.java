package com.flashsale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flashsale.pojo.Stock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockMapper extends BaseMapper<Stock> {
}
