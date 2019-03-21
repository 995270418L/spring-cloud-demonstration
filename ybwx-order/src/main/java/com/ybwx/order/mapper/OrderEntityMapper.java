package com.ybwx.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.ybwx.common.mysql.common.BaseMapper;
import com.ybwx.order.entity.OrderEntity;

@Mapper
public interface OrderEntityMapper extends BaseMapper<OrderEntity> {

}