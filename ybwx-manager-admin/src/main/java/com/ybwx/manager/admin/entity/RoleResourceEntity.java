package com.ybwx.manager.admin.entity;

import com.ybwx.common.mysql.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResourceEntity extends BaseEntity<RoleResourceEntity> {

    private Long resourceId;
    private Long roleId;

}
