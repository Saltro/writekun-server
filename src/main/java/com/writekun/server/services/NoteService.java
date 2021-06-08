package com.writekun.server.services;
import com.writekun.server.entity.Note;
import com.writekun.server.mapper.DatabaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class NoteService {
    @Autowired
    DatabaseMapper databaseMapper;

    public List<Note> getUsersNotes(int userId) {
        return databaseMapper.getNotesByUserId(userId);
    }

    public Note getSingleNote(int noteId) {
        return databaseMapper.getNoteByNoteId(noteId);
    }

    public int insertNote(int userId, Timestamp createDate) {
        String username = "testuser";
        Note note = new Note(0, "", "", "", createDate, createDate, 0, userId, username);
        databaseMapper.insert(note);
        return note.getId();  // 返回插入后得到的自增 ID
    }

    public int updateNote(int noteId, String title, String tag, String text, Timestamp changeDate, int status) {
        databaseMapper.update(noteId, title, tag, text, changeDate, status);
        return 0;
    }
}
