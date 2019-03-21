package com.ybwx.common.mysql.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * BaseEntity
 *
 * @author CuiShuai
 * @date 16/9/12
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity<T> implements Serializable {

    public final static String QUERY_ID = "ids";

    private Long id;

}
