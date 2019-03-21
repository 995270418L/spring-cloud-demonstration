package com.ybwx.multidata.entity;

import com.ybwx.common.mysql.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleEntity extends BaseEntity<UserRoleEntity> {

    private Long roleId;
    private Long userId;

}
