package com.ybwx.manager.admin.security.service;

import com.ybwx.manager.admin.entity.ResourceEntity;
import com.ybwx.manager.admin.entity.RoleResourceEntity;
import com.ybwx.manager.admin.entity.UserEntity;
import com.ybwx.manager.admin.entity.UserRoleEntity;
import com.ybwx.manager.admin.exception.UserNotFoundException;
import com.ybwx.manager.admin.param.ResourceQueryParam;
import com.ybwx.manager.admin.param.RoleResourceQueryParam;
import com.ybwx.manager.admin.param.UserQueryParam;
import com.ybwx.manager.admin.param.UserRoleQueryParam;
import com.ybwx.manager.admin.service.*;
import com.ybwx.manager.admin.vo.Subject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ObjectService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleResourceService roleResourceService;
    @Autowired
    private ResourceService resourceService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UserNotFoundException {
        log.info("use login: {}", s);
        // 这里调用 ldap 获取用户信息
        Set<SimpleGrantedAuthority> roleCollection = new HashSet<>();
        roleCollection.add(new SimpleGrantedAuthority("ADMIN"));
        return new User("steve", "$2a$10$bFJB6f.onGpvkhiFaNsvOOELNRZwFdfSURiTWQuaR5znwTrILy8Dy", roleCollection);
//        UserEntity userEntity = userService.findOne(UserQueryParam.builder().username(s).build());
//        if(userEntity != null) {
//            Subject subject = new Subject();
//            subject.setEnabled(userEntity.getValid() == 1 ? true : false);
//            subject.setUsername(userEntity.getUsername());
//            subject.setPassword(userEntity.getPassword());
//            Map<Long, List<UserRoleEntity>> userRoleMap = userRoleService.findListWithoutPage(UserRoleQueryParam.builder().userId(userEntity.getId()).build()).stream().collect(Collectors.groupingBy(UserRoleEntity::getUserId));
//            List<Long> roleIdSet = userRoleMap.entrySet().stream().map(e -> e.getValue().stream()
//                    .map(userRoleEntity -> userRoleEntity.getRoleId()).collect(Collectors.toList())).flatMap(e -> e.stream()).collect(Collectors.toList());
//            Map<Long, List<RoleResourceEntity>> roleResourceMap = roleIdSet.isEmpty() ? Collections.emptyMap() : roleResourceService.findListWithoutPage(RoleResourceQueryParam.builder().roleIdCollection(roleIdSet).build()).stream().collect(Collectors.groupingBy(RoleResourceEntity::getRoleId));
//            List<Long> resourceIdList = roleResourceMap.isEmpty() ? Collections.emptyList() : roleResourceMap.entrySet().stream()
//                    .map(e -> e.getValue().stream().map(roleResourceEntity -> roleResourceEntity.getResourceId()).collect(Collectors.toList()))
//                    .flatMap(e -> e.stream()).collect(Collectors.toList());
//            List<ResourceEntity> resourceEntityList = resourceIdList.isEmpty() ? Collections.emptyList() : resourceService.findListWithoutPage(ResourceQueryParam.builder().idCollection(resourceIdList).build());
//            List<GrantedAuthority> grantedAuthorities = resourceEntityList.isEmpty() ? Collections.emptyList() : resourceEntityList.stream().map(e -> new SimpleGrantedAuthority(e.getIdentifier())).collect(Collectors.toList());
//            subject.setAuthorities(grantedAuthorities);
//            return subject;
//        } else {
//            throw new UserNotFoundException("该用户不存在");
//        }
    }

}
