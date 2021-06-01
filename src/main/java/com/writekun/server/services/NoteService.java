package com.writekun.server.services;


import com.writekun.server.entity.Note;
import com.writekun.server.mapper.DatabaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NoteService {
    @Autowired
    DatabaseMapper databaseMapper;

    public void insertNote(Note note) {
        databaseMapper.insert(note);
//        System.out.println(note.getId());  // 输出插入后得到的自增 ID
    }
}
