package com.ybwx.common.mysql.common.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ybwx.common.mysql.common.*;
import com.ybwx.common.mysql.exception.DbServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class BaseServiceImpl<T extends BaseEntity<T>, M extends BaseMapper<T>> implements BaseService<T> {

    @Autowired
    private M mapper;

    private static final String OUTOFSIZE = "查询到大于1条的记录";

    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public T insert(T entity) {
        int row = mapper.insert(entity);
        if(hasMetaData()){
            insertData(entity);
        }
        return null;
    }

    @Override
    public T findOne(QueryParam queryParam) {
        List<T> list = findListWithoutPage(queryParam);
        if (list.isEmpty()) {
            return null;
        } else if (list.size() > 1) {
            throw new DbServiceException(OUTOFSIZE);
        }
        return list.get(0);
    }

    public T selectByPrimaryKey(Long id, boolean hasData) {
        T entity = mapper.selectByPrimaryKey(id);
        if(entity == null) return null;
        if(hasData){
            List<T> list = Lists.newArrayList();
            list.add(entity);
            putData(list);
        }
        return entity;
    }

    private void putData(List<T> list) {
        if(!hasMetaData()) return;
        BaseMapper<BaseDataEntity> dataMapper = getDataMapper();
        if (dataMapper == null) {
            log.error("No DataMapper Found!");
            return;
        }
        List<Object> ids = Lists.newArrayList();
        list.stream().map(e -> {
            try {
                return ids.add(e.getClass().getMethod("getId").invoke(e));
            } catch (Exception e1) {
                e1.printStackTrace();
            }finally {
                return null;
            }
        });
        if (ids.isEmpty()) {
            log.error("通过主键查询到的数据为空");
            return;
        }
        QueryDataParam queryDataParam = new QueryDataParam();
        queryDataParam.setIds(ids);
        List<BaseDataEntity> dataList = dataMapper.selectList(queryDataParam);
        if (dataList == null || dataList.isEmpty()) {
            return;
        }
        putDataList(list, dataList, getKeyMap());
    }

    private void putDataList(List<T> entityList, List<BaseDataEntity> dataList, Map<Integer,String> dataFieldMap) {
        Map<String, String> dataMap = Maps.newHashMap();
        dataList.stream().map(e ->
            dataMap.put(buildUniqueKey(e.getId(), e.getMid()), e.getData())
        );

        for(T entity : entityList){
            dataFieldMap.entrySet().forEach(e  -> {
                try {
                    Method method = entity.getClass().getMethod("set" + e.getValue(), String.class);
                    method.invoke(entity, dataMap.get(buildUniqueKey(entity.getClass().getMethod("getId").invoke(entity), e.getKey())));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
        }
    }

    private String buildUniqueKey(Object id, Integer metaId) {
        return id + "_" + metaId;
    }

    @Override
    public T selectByPrimaryKey(Long id) {
        return selectByPrimaryKey(id, hasMetaData());
    }

    @Override
    public int update(T entity) {
        int row = mapper.update(entity);
        if (hasMetaData()) {
            getDataMapper().deleteByPrimaryKey(entity.getId());
            insertData(entity);
        }
        return 0;
    }


    private void insertData(T entity) {
        for (Map.Entry<Integer, String> entry : getKeyMap().entrySet()) {
            Integer mid = entry.getKey();
            String field = entry.getValue();
            String data = null;
            try {
                Object value = entity.getClass().getMethod("get" + field).invoke(entity);
                if (value != null) data = String.valueOf(value);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException("invoke method [get" + field + "] error!");
            }
            getDataMapper().insert(newDataEntity(entity.getId(), mid, data));
        }
    }

    protected Map<Integer, String> getKeyMap() {
        return null;
    }

    protected BaseDataEntity newDataEntity(Long id, Integer mid, String value) {
        return null;
    }

    protected BaseMapper<BaseDataEntity> getDataMapper() {
        return null;
    }

    private boolean hasMetaData() {
        return false;
    }

    @Override
    public Page findListByPage(QueryParam queryParam, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Page<T> list = mapper.selectList(queryParam);
        if (list == null || list.isEmpty()) {
            return new Page<>(pageNo, pageSize);
        }
        return list;
    }

    @Override
    public List findListWithoutPage(QueryParam queryParam) {
        if (queryParam == null) {
            return Collections.emptyList();
        }
        List<T> list = mapper.selectList(queryParam);
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        if (queryParam.hasData()) {
            putData(list);
        }
        return list;
    }


    @Override
    public Map findDtoMap(QueryParam queryParam) {
        if (queryParam == null) {
            return Collections.emptyMap();
        }
        List<T> list = mapper.selectList(queryParam);
        if (list == null || list.isEmpty()) {
            return Collections.emptyMap();
        }
        Map result = list.stream().collect(Collectors.toMap(e -> e.getId(), e -> e, (v1,v2) -> v1));
        if (queryParam.hasData()) {
            putData(list);
        }
        return result;
    }

    @Override
    public int deleteByPrimaryKeyIn(List ids) {
        return mapper.deleteByPrimaryKeyIn(ids);
    }
}
