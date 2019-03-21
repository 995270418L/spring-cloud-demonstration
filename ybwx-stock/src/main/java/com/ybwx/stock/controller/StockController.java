package com.ybwx.stock.controller;

import com.ybwx.common.api.spark.OrderInvoke;
import com.ybwx.common.api.spark.OrderResult;
import com.ybwx.stock.entity.StockEntity;
import com.ybwx.stock.param.StockQueryParam;
import com.ybwx.stock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public OrderResult consume(@RequestBody OrderInvoke orderInvoke){
        StockEntity stockEntity = stockService.findOne(StockQueryParam.builder().productId(orderInvoke.getProductId()).build());
        log.info("minus stock");
        if(stockEntity.getLeftNum() > orderInvoke.getStockMinusNum()){
            stockEntity.setLeftNum(stockEntity.getLeftNum() - orderInvoke.getStockMinusNum());
            stockService.update(stockEntity);
            return new OrderResult(true);
        }else{
            log.error("comsume stock failed ...");
            return new OrderResult(false);
        }
    }
}
