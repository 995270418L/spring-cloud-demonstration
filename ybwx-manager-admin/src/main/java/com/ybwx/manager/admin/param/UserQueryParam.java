package com.ybwx.manager.admin.param;

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
public class UserQueryParam implements QueryParam {

    private Long id;
    private Collection<Long> idCollection;
    private String username;

    @Override
    public boolean hasData() {
        return false;
    }

}