package com.ybwx.multidata.entity;

import com.ybwx.common.enums.EnableDisableStatus;
import com.ybwx.common.enums.YesNoStatus;
import com.ybwx.common.mysql.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity extends BaseEntity<RoleEntity> {

    private Long id;
    private Date createdTime;
    private String description;
    private String identifier;
    private YesNoStatus isDefault;
    private String name;
    private Date updatedTime;
    private EnableDisableStatus valid;

}
