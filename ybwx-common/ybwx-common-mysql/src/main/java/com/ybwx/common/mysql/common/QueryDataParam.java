package com.ybwx.common.mysql.common;

import lombok.Data;

import java.util.Collection;

/**
 * @描述 :
 * @作者 :	pst
 * @日期 :	2017/10/10
 * @时间 :	10:36
 */
@Data
public class QueryDataParam implements QueryParam {

    private Collection<Object> ids;

    @Override
    public boolean hasData() {
        return false;
    }
}
