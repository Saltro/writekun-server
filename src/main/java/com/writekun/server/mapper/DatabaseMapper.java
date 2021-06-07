package com.writekun.server.mapper;

import com.writekun.server.entity.Note;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DatabaseMapper {
    @Select("SELECT id,title,tag,text,date,status,user_id,username FROM note WHERE id=#{noteId}")
    public Note getSingleNote(int noteId);

    @Insert("INSERT INTO note(title,tag,text,date,status,user_id,username) VALUES(#{title},#{tag},#{text},#{date, jdbcType=DATE},#{status},#{user_id},#{username})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")  // keyProperty java对象的属性；keyColumn表示数据库的字段，此处表示按照id值自增
    public void insert(Note note);

    @Update("UPDATE note SET title=#{title},tag=#{tag},text=#{text},status=#{status} WHERE id=#{id}")
    public void update(Note note);
}
