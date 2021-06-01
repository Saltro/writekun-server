package com.writekun.server.mapper;

import com.writekun.server.entity.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface DatabaseMapper {
    @Insert("INSERT INTO note(title,tag,text,date,status,user_id,username) VALUES(#{title},#{tag},#{text},#{date, jdbcType=DATE},#{status},#{user_id},#{username})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")  // keyProperty java对象的属性；keyColumn表示数据库的字段，此处表示按照id值自增
    public void insert(Note note);
}
