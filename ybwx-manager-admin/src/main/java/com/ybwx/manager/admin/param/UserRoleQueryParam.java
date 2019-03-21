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
public class UserRoleQueryParam implements QueryParam {

    private Long userId;
    @Override
    public boolean hasData() {
        return false;
    }

}