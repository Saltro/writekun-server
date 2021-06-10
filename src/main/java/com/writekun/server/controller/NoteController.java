package com.writekun.server.controller;

import com.writekun.server.entity.Note;
import com.writekun.server.exception.NoteException;
import com.writekun.server.exception.NoteNotFoundException;
import com.writekun.server.services.NoteService;
import com.writekun.server.utils.JSONReturnBody;
import com.writekun.server.utils.ReturnStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api/note")
public class NoteController {
    NoteService noteService;

    @Autowired  // 推荐使用构造器注入
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/get-my-notes")
    public JSONReturnBody<Object> getMyNotes(@RequestParam int userId) {
        try {
            List<Note> l = noteService.getUsersBasicNotes(userId);
            List<Map<String, Object>> data = new ArrayList<>();
            for (Note note: l) {
                Map<String, Object> addData = new HashMap<>();
                addData.put("noteId", note.getId());
                addData.put("title", note.getTitle());
                addData.put("tag", note.getTag());
                addData.put("createDate", note.getCreateDate());
                addData.put("changeDate", note.getChangeDate());
                addData.put("status", note.getStatus());
                data.add(addData);
            }
            return new JSONReturnBody<>(ReturnStatus.RESPONSE_SUCCESS, data);
        } catch (NoteNotFoundException e) {
            return new JSONReturnBody<>(ReturnStatus.NOTE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e);
            return new JSONReturnBody<>(ReturnStatus.UNKNOWN_ERROR);
        }
    }

    @GetMapping("/get-note")
    public JSONReturnBody<Object> getSingleNote(@RequestParam int noteId) {
        try {
            Note note = noteService.getSingleNote(noteId);
            return new JSONReturnBody<>(ReturnStatus.RESPONSE_SUCCESS, note);
        } catch (NoteNotFoundException e) {
            return new JSONReturnBody<>(ReturnStatus.NOTE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e);
            return new JSONReturnBody<>(ReturnStatus.UNKNOWN_ERROR);
        }
    }

    @PostMapping("/create")
    public JSONReturnBody<Object> createNote(@RequestBody Map<String, Object> params) {
        int userId = (int) params.get("userId");
        Timestamp date = new Timestamp(new Date().getTime());
        try {
            int noteId = noteService.insertNote(userId, date);
            Map<String, Object> data = new HashMap<>();
            data.put("noteId", noteId);
            data.put("createDate", date);
            return new JSONReturnBody<>(ReturnStatus.RESOURCE_CHANGED, data);
        } catch (NoteException e) {
            return new JSONReturnBody<>(ReturnStatus.NOTE_ERROR);
        } catch (Exception e) {
            System.out.println(e);
            return new JSONReturnBody<>(ReturnStatus.UNKNOWN_ERROR);
        }
    }

    @PostMapping("/upload")
    public JSONReturnBody<Object> insertNote(@RequestBody Map<String, Object> params) {
        int noteId = (int) params.get("noteId");
        String title = (String) params.get("title");
        String tag = (String) params.get("tag");
        String text = (String) params.get("text");
        Timestamp changeDate = new Timestamp(new Date().getTime());
        int status = (int) params.get("status");
        try {
            noteService.updateNote(noteId, title, tag, text, changeDate, status);
            Map<String, Object> data = new HashMap<>();
            data.put("noteId", noteId);
            data.put("changeDate", changeDate);
            return new JSONReturnBody<>(ReturnStatus.RESOURCE_CHANGED, data);
        } catch (NoteNotFoundException e) {
            return new JSONReturnBody<>(ReturnStatus.NOTE_NOT_FOUND);
        } catch (NoteException e) {
            return new JSONReturnBody<>(ReturnStatus.NOTE_ERROR);
        } catch (Exception e) {
            System.out.println(e);
            return new JSONReturnBody<>(ReturnStatus.UNKNOWN_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public JSONReturnBody<Object> deleteNote(@RequestParam int noteId) {
        try {
            noteService.deleteNote(noteId);
            return new JSONReturnBody<>(ReturnStatus.RESOURCE_CHANGED);
        } catch (NoteNotFoundException e) {
            return new JSONReturnBody<>(ReturnStatus.NOTE_NOT_FOUND);
        } catch (NoteException e) {
            return new JSONReturnBody<>(ReturnStatus.NOTE_ERROR);
        } catch (Exception e) {
            System.out.println(e);
            return new JSONReturnBody<>(ReturnStatus.UNKNOWN_ERROR);
        }
    }
}
