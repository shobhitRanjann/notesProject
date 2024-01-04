package com.notesManager.notesManager.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
}
