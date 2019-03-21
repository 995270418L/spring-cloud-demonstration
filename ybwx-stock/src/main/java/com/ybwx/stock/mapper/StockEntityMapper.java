package com.ybwx.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.ybwx.common.mysql.common.BaseMapper;
import com.ybwx.stock.entity.StockEntity;

@Mapper
public interface StockEntityMapper extends BaseMapper<StockEntity> {

}