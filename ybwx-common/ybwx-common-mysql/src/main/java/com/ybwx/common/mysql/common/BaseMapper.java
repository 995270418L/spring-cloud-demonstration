package com.ybwx.common.mysql.common;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BaseMapper<T> {

    int deleteByPrimaryKey(Long id);

    int deleteByPrimaryKeyIn(@Param("ids") List<Long> ids);

    int insert(T record);

//    int insertSelective(T record);

    T selectByPrimaryKey(Long id);

    int update(T record);

//    PageList<T> findListByPage(Map<String, Object> paramMap, PageBounds pageBounds);

//    List<T> findListWithoutPage(Map<String, Object> paramMap);

//    PageList<T> findListByPage(QueryParam queryParam, PageBounds pageBounds);

    Page<T> selectList(QueryParam queryParam);

}
