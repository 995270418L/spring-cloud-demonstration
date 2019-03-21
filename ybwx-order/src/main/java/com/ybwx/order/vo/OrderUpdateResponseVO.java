package com.ybwx.order.vo;

import com.ybwx.order.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateResponseVO {

    private Long id;
    private Long productId;
    private Long userId;
    private Date createTime;
    private Date updateTime;

    public static OrderUpdateResponseVO valueOf(OrderEntity orderEntity){
        return new OrderUpdateResponseVO(
                orderEntity.getId(),
                orderEntity.getProductId(),
                orderEntity.getUserId(),
                orderEntity.getCreateTime(),
                orderEntity.getUpdateTime()
        );
    }

}
