package com.ybwx.multidata.service.impl;

import com.ybwx.common.mysql.common.impl.BaseServiceImpl;

import com.ybwx.multidata.entity.UserEntity;
import com.ybwx.multidata.mapper.UserEntityMapper;
import com.ybwx.multidata.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl extends BaseServiceImpl<UserEntity, UserEntityMapper> implements UserService {

}