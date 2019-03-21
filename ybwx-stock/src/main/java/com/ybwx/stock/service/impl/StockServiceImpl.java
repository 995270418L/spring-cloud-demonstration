package com.ybwx.stock.service.impl;

import com.ybwx.common.mysql.common.impl.BaseServiceImpl;
import com.ybwx.stock.entity.StockEntity;
import com.ybwx.stock.mapper.StockEntityMapper;
import com.ybwx.stock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StockServiceImpl extends BaseServiceImpl<StockEntity, StockEntityMapper> implements StockService {

}