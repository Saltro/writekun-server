package com.writekun.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    private int id;
    private String title;
    private String tag;
    private String text;
    private Date date;
    private int status;
    private int user_id;
    private String username;
}
