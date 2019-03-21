package com.ybwx.stock.param;

import com.ybwx.common.mysql.common.QueryParam;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockQueryParam implements QueryParam {

    private Long id;
    private Collection<Long> idCollection;
    private Long productId;

    @Override
    public boolean hasData() {
        return false;
    }

}