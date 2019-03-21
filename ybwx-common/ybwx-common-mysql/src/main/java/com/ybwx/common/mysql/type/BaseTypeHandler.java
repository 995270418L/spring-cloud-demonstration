package com.ybwx.common.mysql.type;

import com.ybwx.common.enums.BaseEnumInterface;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * generator 自定义类型转换器
 */
public class BaseTypeHandler<T extends BaseEnumInterface> implements TypeHandler<T>, InitializingBean {

    private Class<T> handlerClz;

    public BaseTypeHandler() {
        try {
            this.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            ps.setInt(i, parameter.getValue());
        } else {
            ps.setNull(i, 0);
        }
    }

    @Override
    public T getResult(ResultSet rs, String columnName) throws SQLException {
        Integer value;
        value = rs.getInt(columnName);
        return getT(value);
    }

    @Override
    public T getResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer value;
        value = rs.getInt(columnIndex);
        return getT(value);
    }

    @Override
    public T getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer value;
        value = cs.getInt(columnIndex);
        return getT(value);
    }

    private T getT(Integer value) {
        try {
            Method method;
            try {
                method = handlerClz.getMethod("get", Integer.class);
            } catch (NoSuchMethodException e) {
                method = handlerClz.getMethod("get", int.class);
            }
            T result = (T) method.invoke(null, value);
            return result;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        handlerClz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
