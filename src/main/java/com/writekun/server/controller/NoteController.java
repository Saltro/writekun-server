package com.writekun.server.controller;

import com.writekun.server.entity.Note;
import com.writekun.server.services.NoteService;
import com.writekun.server.utils.JSONReturnBody;
import com.writekun.server.utils.ReturnStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/note")
public class NoteController {
    @Autowired
    NoteService noteService;

    @GetMapping("/get-note")
    public JSONReturnBody<Object> getSingleNote(@RequestParam int noteId) {
        Note note = noteService.getSingleNote(noteId);
        Map<String, Object> data = new HashMap<>();
        data.put("noteId", note.getId());
        data.put("title", note.getTitle());
        data.put("tag", note.getTag());
        data.put("text", note.getText());
        data.put("date", note.getDate());
        data.put("status", note.getStatus());
        data.put("userId", note.getUser_id());
        return new JSONReturnBody<>(ReturnStatus.RESPONSE_SUCCESS, data);
    }

    @PostMapping("/create")
    public JSONReturnBody<Object> createNote(@RequestBody Map<String, Object> params) {
        int userId = (int) params.get("userId");
        Date date = new Date();
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
        int status = (int) params.get("status");
        int userId = (int) params.get("userId");
        noteService.updateNote(noteId, title, tag, text, status, userId);
        return new JSONReturnBody<>(ReturnStatus.RESOURCE_CHANGED);
    }
}
