package com.writekun.server.services;

import com.writekun.server.entity.Note;
import com.writekun.server.exception.NoteException;
import com.writekun.server.exception.NoteNotFoundException;
import com.writekun.server.mapper.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
public class NoteService {
    NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getUsersBasicNotes(int userId) throws NoteNotFoundException {
        List<Note> ret = noteRepository.findBasicNotesByUserId(userId);
        if (ret.isEmpty()) {
            throw new NoteNotFoundException();
        }
        return ret;
    }

    public Note getSingleNote(int noteId) throws NoteNotFoundException {
        Note ret = noteRepository.findNoteByNoteId(noteId);
        if (ret == null) {
            throw new NoteNotFoundException();
        }
        return ret;
    }

    public int insertNote(int userId, Timestamp createDate) throws NoteException {
        String username = "testuser";
        Note note = new Note(0, "", "", "", createDate, createDate, 0, userId, username);
        int insertResult = noteRepository.insert(note);
        if (insertResult != 1) {
            throw new NoteException();
        }
        return note.getId();  // 返回插入后得到的自增 ID
    }

    public void updateNote(int noteId, String title, String tag, String text, Timestamp changeDate, int status) throws NoteNotFoundException, NoteException {
        int updateResult = noteRepository.update(noteId, title, tag, text, changeDate, status);
        if (updateResult == 0) {
            throw new NoteNotFoundException();
        } else if (updateResult != 1) {
            throw new NoteException();
        }
    }

    public void deleteNote(int noteId) throws NoteNotFoundException, NoteException {
        int deleteResult = noteRepository.delete(noteId);
        if (deleteResult == 0) {
            throw new NoteNotFoundException();
        } else if (deleteResult != 1) {
            throw new NoteException();
        }
    }
}
