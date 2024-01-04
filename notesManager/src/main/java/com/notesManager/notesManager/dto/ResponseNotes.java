package com.notesManager.notesManager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseNotes {
    public String title;
    public String description;
}
