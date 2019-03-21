package com.ybwx.manager.admin.service.impl;

import com.ybwx.common.mysql.common.impl.BaseServiceImpl;
import com.ybwx.manager.admin.entity.UserEntity;
import com.ybwx.manager.admin.mapper.UserEntityMapper;
import com.ybwx.manager.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl extends BaseServiceImpl<UserEntity, UserEntityMapper> implements UserService {

}