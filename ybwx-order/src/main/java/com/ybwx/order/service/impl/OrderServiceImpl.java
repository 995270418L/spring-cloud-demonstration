package com.ybwx.order.service.impl;

import com.ybwx.common.mysql.common.impl.BaseServiceImpl;
import com.ybwx.order.entity.OrderEntity;
import com.ybwx.order.mapper.OrderEntityMapper;
import com.ybwx.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl extends BaseServiceImpl<OrderEntity, OrderEntityMapper> implements OrderService {

}