package com.ybwx.common.util.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @描述 :
 * @作者 :	pst
 * @日期 :	2017/10/13
 * @时间 :	16:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVO<T> {

    private Integer page;
    private Integer size;
    private Integer count;
    private List<T> list;

}
