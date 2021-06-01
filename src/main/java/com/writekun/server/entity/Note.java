package com.writekun.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    public int id;
    public String title;
    public String tag;
    public String text;
    public Date date;
    public int status;
    public int user_id;
    public String username;
}
