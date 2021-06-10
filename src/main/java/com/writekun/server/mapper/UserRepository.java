package com.writekun.server.mapper;

import com.writekun.server.security.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRepository {
    @Select("SELECT user_id userId,username,password,email,role,is_active isActive FROM user WHERE username=#{username}")
    public UserEntity findUserByUsername(String username);

    @Select("SELECT authority FROM role WHERE role_name=#{role}")
    public List<String> findAuthoritiesByRole(String role);

    @Insert("INSERT INTO user(username,password,email,role,is_active) VALUES(#{username},#{password},#{email},#{role},#{isActive})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
    public int insert(UserEntity user);
}
