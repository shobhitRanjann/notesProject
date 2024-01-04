package com.notesManager.notesManager.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RequestNotesData {
    public long id;
    public String title;
    public String description;
    public Date date;
}
