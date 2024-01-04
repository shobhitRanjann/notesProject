package com.notesManager.notesManager.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();

    long getUserIdByUsername(String username);
}
