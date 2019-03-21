package com.ybwx.common.util.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @描述 :
 * @作者 :	pst
 * @日期 :	2017/9/26
 * @时间 :	20:11
 */
@Data
public class CommonRequestVO {

    @JsonProperty("page")
    private Integer pageNo;
    @JsonProperty("size")
    private Integer pageSize;

    public void normalized() {
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 20;
        }
    }
}
