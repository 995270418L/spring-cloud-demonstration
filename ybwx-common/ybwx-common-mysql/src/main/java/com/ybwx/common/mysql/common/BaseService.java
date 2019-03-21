package com.ybwx.common.mysql.common;

import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {
    int deleteByPrimaryKey(Long id);

    int deleteByPrimaryKeyIn(List<Long> ids);

    T insert(T entity);

    T findOne(QueryParam queryParam);

    T selectByPrimaryKey(Long id);

    int update(T entity);


//    Page<T> findListByPage(Map<String, Object> paramMap, int pageNo, int pageSize);

//    List<T> findListWithoutPage(Map<String, Object> paramMap);

    Page<T> findListByPage(QueryParam queryParam, int pageNo, int pageSize);

    List<T> findListWithoutPage(QueryParam queryParam);

    Map<Long, T> findDtoMap(QueryParam queryParam);

}
