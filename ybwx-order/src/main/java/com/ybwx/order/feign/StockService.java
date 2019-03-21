package com.ybwx.order.feign;

import com.ybwx.common.api.spark.OrderInvoke;
import com.ybwx.common.api.spark.OrderResult;
import com.ybwx.common.util.MicroService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Configuration
@FeignClient(name = MicroService.YBWX_STOCK, decode404 = true, path = "stock")
public interface StockService {

    @PostMapping(value = "update")
    OrderResult consumeProduct(OrderInvoke orderInvoke);

}
