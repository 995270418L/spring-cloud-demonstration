package com.ybwx.common.api.spark;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResult {

    /**
     * judge consume successful
     */
    private Boolean consume;

}
