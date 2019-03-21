package com.ybwx.manager.admin.service.impl;

import com.ybwx.common.mysql.common.impl.BaseServiceImpl;
import com.ybwx.manager.admin.entity.ResourceEntity;
import com.ybwx.manager.admin.mapper.ResourceEntityMapper;
import com.ybwx.manager.admin.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResourceServiceImpl extends BaseServiceImpl<ResourceEntity, ResourceEntityMapper> implements ResourceService {

}