package com.writekun.server.controller;

import com.writekun.server.entity.Note;
import com.writekun.server.services.NoteService;
import com.writekun.server.utils.JSONReturnBody;
import com.writekun.server.utils.ReturnStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/note")
public class NoteController {
    @Autowired
    NoteService noteService;

    @GetMapping("/upload")
    public JSONReturnBody<Object> insertNote(@RequestParam(value="title",required = false) String title,
                                     @RequestParam(value="tag",required = false) String tag,
                                     @RequestParam(value="text",required = false) String text) {
        int status = 0;
        int user_id = 2;
        String username = "user";
        noteService.insertNote(new Note(0, title, tag, text, new Date(), status, user_id, username));
        return new JSONReturnBody<>(ReturnStatus.RESOURCE_CHANGED);
    }
}
