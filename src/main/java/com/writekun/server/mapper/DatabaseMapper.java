package com.writekun.server.mapper;

import com.writekun.server.entity.Note;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface DatabaseMapper {
    @Select("SELECT id,title,tag,text,create_date createDate,change_date changeDate,status,user_id userId,username FROM note WHERE user_id=#{userId}")
    public List<Note> getNotesByUserId(int userId);

    @Select("SELECT id,title,tag,text,create_date createDate,change_date changeDate,status,user_id userId,username FROM note WHERE id=#{noteId}")
    public Note getNoteByNoteId(int noteId);

    @Insert("INSERT INTO note(create_date,change_date,user_id,username) VALUES(#{createDate, jdbcType=TIMESTAMP},#{changeDate, jdbcType=DATE},#{userId},#{username})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")  // keyProperty java对象的属性；keyColumn表示数据库的字段，此处表示按照id值自增
    public void insert(Note note);

    @Update("UPDATE note SET title=#{title},tag=#{tag},text=#{text},change_date=#{changeDate, jdbcType=TIMESTAMP},status=#{status} WHERE id=#{id}")
    public void update(int id, String title, String tag, String text, Timestamp changeDate, int status);
}
