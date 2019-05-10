package com.ybwx.spring.mybatis.service;

import com.ybwx.spring.mybatis.entity.UserEntity;
import com.ybwx.spring.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserEntity findById(Long id){
//        SqlSessionFactory sqlSessionFactory = springService.getBean(SqlSessionFactory.class);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        UserEntity userEntity =  userMapper.selectById(id);
//        sqlSession.close();
        UserEntity userEntity = userMapper.selectById(id);
        return userEntity;
    }

    public void save(){
        log.info("insert user object");
        userMapper.save(new UserEntity(null, "steve", "steve"));
    }

}
