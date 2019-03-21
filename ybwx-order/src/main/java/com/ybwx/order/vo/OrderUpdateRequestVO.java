package com.ybwx.order.vo;

import lombok.Data;

@Data
public class OrderUpdateRequestVO {

    private Long id;
    private Long productId;
    private Long userId;
}
