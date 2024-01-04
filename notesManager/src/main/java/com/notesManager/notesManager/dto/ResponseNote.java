package com.notesManager.notesManager.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ResponseNote {
    public long id;
    public String title;
    public String description;
    public long userId;
    public Date date;

}
