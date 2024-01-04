package com.notesManager.notesManager.external.client;

import com.notesManager.notesManager.exception.ClientException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "USER-DATA/api/auth")
public interface UserService {
    @PostMapping("/getuserid")
    public long getUserIdByUsername(@RequestParam("username") String username);

    default void fallback(Exception e) throws ClientException {
        throw new ClientException("User Service Is Down","Unavailable",500);
    }
}
