package com.ybwx.stock.entity;

import java.util.Date;
import com.ybwx.common.mysql.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockEntity extends BaseEntity<StockEntity> {

    private Long id;
    private Long productId;
    private Long totalNum;
    private Long leftNum;
    private Date createTime;
    private Date updateTime;

}
