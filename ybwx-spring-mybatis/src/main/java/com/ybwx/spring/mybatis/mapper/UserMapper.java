package com.ybwx.spring.mybatis.mapper;

import com.ybwx.spring.mybatis.entity.UserEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password")
    })
    @Select("select * from user where id=#{id}")
    UserEntity selectById(Long id);

    @Insert("insert into user(username,password) value(#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(UserEntity userEntity);
}
