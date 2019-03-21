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
public class RoleResourceQueryParam implements QueryParam {

    private Long roleId;
    private Collection<Long> roleIdCollection;

    @Override
    public boolean hasData() {
        return false;
    }

}