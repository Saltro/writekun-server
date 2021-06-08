package com.writekun.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    private int id;
    private String title;
    private String tag;
    private String text;
    private Timestamp createDate;
    private Timestamp changeDate;
    private int status;
    private int userId;
    private String username;
}
