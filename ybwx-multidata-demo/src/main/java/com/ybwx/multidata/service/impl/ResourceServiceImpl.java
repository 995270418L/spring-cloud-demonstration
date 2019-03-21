package com.ybwx.multidata.service.impl;

import com.ybwx.common.mysql.common.impl.BaseServiceImpl;
import com.ybwx.multidata.entity.ResourceEntity;
import com.ybwx.multidata.mapper.ResourceEntityMapper;
import com.ybwx.multidata.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResourceServiceImpl extends BaseServiceImpl<ResourceEntity, ResourceEntityMapper> implements ResourceService {

}