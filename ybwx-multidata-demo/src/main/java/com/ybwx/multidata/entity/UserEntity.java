package com.ybwx.multidata.entity;

import com.ybwx.common.mysql.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity<UserEntity> {

    private Long id;
    private Date createdTime;
    private String email;
    private Integer emailValid;
    private String mobile;
    private Integer mobileValid;
    private String password;
    private Integer root;
    private String salt;
    private Date updatedTime;
    private String username;
    private Integer valid;
    private String token;

}
