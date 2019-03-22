package com.ybwx.order.controller;

import com.ybwx.common.api.spark.OrderInvoke;
import com.ybwx.common.api.spark.OrderResult;
import com.ybwx.common.enums.ResultStatusEnum;
import com.ybwx.common.enums.YesNoStatus;
import com.ybwx.common.util.vo.ResultVO;
import com.ybwx.order.entity.OrderEntity;
import com.ybwx.order.feign.StockService;
import com.ybwx.order.service.OrderService;
import com.ybwx.order.vo.OrderUpdateRequestVO;
import com.ybwx.order.vo.OrderUpdateResponseVO;
import com.ybwx.order.vo.OrderUsageRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

@Slf4j
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private StockService stockService;

    @RequestMapping("/update")
    public ResultVO<?> update(@RequestBody @Valid OrderUpdateRequestVO requestVO) {
        Date date = new Date();
        OrderEntity orderEntity = new OrderEntity(
                requestVO.getId(),
                requestVO.getProductId(),
                requestVO.getUserId(),
                YesNoStatus.NO,
                date,
                date
        );
        return new ResultVO<>(OrderUpdateResponseVO.valueOf(orderEntity));
    }

    /**
     * order usage
     * @param requestVO
     * @return
     */
    @RequestMapping("/calculate")
    public ResultVO<?> calculate(@RequestBody @Valid OrderUsageRequestVO requestVO) {
        OrderEntity orderEntity = orderService.selectByPrimaryKey(requestVO.getOrderId());
        orderEntity.setPaymentType(YesNoStatus.YES);
        OrderResult orderResult = stockService.consumeProduct(new OrderInvoke(orderEntity.getProductId(), requestVO.getProductNum()));
        if(orderResult.getConsume()){
            orderService.update(orderEntity);
        }
        return new ResultVO<>(ResultStatusEnum.OK);
    }

}
