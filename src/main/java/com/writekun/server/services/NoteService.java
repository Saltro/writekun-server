package com.writekun.server.services;


import com.writekun.server.entity.Note;
import com.writekun.server.mapper.DatabaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class NoteService {
    @Autowired
    DatabaseMapper databaseMapper;

    public Note getSingleNote(int noteId) {
        Note note = databaseMapper.getSingleNote(noteId);
        return note;
    }

    public int insertNote(int userId, Date createDate) {
        String username = "testuser";
        Note note = new Note(0, "", "", "", createDate, 0, userId, username);
        databaseMapper.insert(note);
        return note.getId();  // 返回插入后得到的自增 ID
    }

    public int updateNote(int noteId, String title, String tag, String text, int status, int userId) {
        Note note = new Note(noteId, title, tag, text, new Date(), status, userId, "");
        databaseMapper.update(note);
        return 0;
    }
}
