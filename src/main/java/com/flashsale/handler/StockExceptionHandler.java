package com.flashsale.handler;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class StockExceptionHandler {

    @ExceptionHandler(BizException.class)
    public Response handlerStockException(BizException e) {

        // 根据不同异常细分状态码返回不同的提示
        if (Objects.equals(e.getErrCode(), "STOCK_SALE_ERROR")) {
            log.info("下单失败");
            return Response.buildFailure("500", "下单失败");
        }
        return Response.buildFailure("500", "服务器繁忙，请稍后重试...");
    }
}