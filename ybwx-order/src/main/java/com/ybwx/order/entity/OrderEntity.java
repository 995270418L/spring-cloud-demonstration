package com.ybwx.order.entity;

import com.ybwx.common.enums.YesNoStatus;
import java.util.Date;
import com.ybwx.common.mysql.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity extends BaseEntity<OrderEntity> {

    private Long id;
    private Long productId;
    private Long userId;
    private YesNoStatus paymentType;
    private Date createTime;
    private Date updateTime;

}
