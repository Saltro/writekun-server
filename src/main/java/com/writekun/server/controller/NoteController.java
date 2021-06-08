package com.writekun.server.controller;

import com.writekun.server.entity.Note;
import com.writekun.server.services.NoteService;
import com.writekun.server.utils.JSONReturnBody;
import com.writekun.server.utils.ReturnStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Note> l = noteService.getUsersNotes(userId);
        return new JSONReturnBody<>(ReturnStatus.RESPONSE_SUCCESS, l);
    }

    @GetMapping("/get-note")
    public JSONReturnBody<Object> getSingleNote(@RequestParam int noteId) {
        Note note = noteService.getSingleNote(noteId);
        return new JSONReturnBody<>(ReturnStatus.RESPONSE_SUCCESS, note);
    }

    @PostMapping("/create")
    public JSONReturnBody<Object> createNote(@RequestBody Map<String, Object> params) {
        int userId = (int) params.get("userId");
        Timestamp date = new Timestamp(new Date().getTime());
        int noteId = noteService.insertNote(userId, date);
        Map<String, Object> data = new HashMap<>();
        data.put("noteId", noteId);
        data.put("createDate", date);
        data.put("userId", userId);
        return new JSONReturnBody<>(ReturnStatus.RESOURCE_CHANGED, data);
    }

    @PostMapping("/upload")
    public JSONReturnBody<Object> insertNote(@RequestBody Map<String, Object> params) {
        int noteId = (int) params.get("noteId");
        String title = (String) params.get("title");
        String tag = (String) params.get("tag");
        String text = (String) params.get("text");
        Timestamp changeDate = new Timestamp(new Date().getTime());
        int status = (int) params.get("status");
        noteService.updateNote(noteId, title, tag, text, changeDate, status);
        return new JSONReturnBody<>(ReturnStatus.RESOURCE_CHANGED);
    }
}
