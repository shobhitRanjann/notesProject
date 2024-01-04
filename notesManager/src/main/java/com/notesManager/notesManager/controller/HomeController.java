package com.notesManager.notesManager.controller;

import com.notesManager.notesManager.dto.*;
import com.notesManager.notesManager.entity.Notes;
import com.notesManager.notesManager.service.NotesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class HomeController {

    @Autowired
    public NotesManager notesManager;

    @GetMapping("notes")
    public List<RequestNotesData> listOfAllNotes(@RequestHeader("Authorization") String token){
        return notesManager.listOfNotes(token);
    }
    @GetMapping("notes/{id}")
    public RequestNotesData getNoteById(@RequestHeader("Authorization") String token, @PathVariable("id") long id){
        return notesManager.getNoteById(token,id);
    }

    @PostMapping("notes")
    public String store(@RequestHeader("Authorization") String token, @RequestBody RequestData data) {
        return notesManager.store(token,data);
    }

    @PutMapping("notes/{id}")
    public Notes updateNoteById(@RequestHeader("Authorization") String token,@PathVariable("id") String id,@RequestBody RequestNoteData requestNoteData){
        return notesManager.updateNoteById(token,id,requestNoteData);
    }

    @DeleteMapping("notes/{id}")
    public String deleteNoteById(@RequestHeader("Authorization") String token, @PathVariable("id") String id){
        return notesManager.deleteNoteById(token,id);
    }

    @PostMapping("notes/{id}/share")
    public ResponseNote shareNoteWithUser(@RequestHeader("Authorization") String token,@PathVariable("id") long id){
        return notesManager.shareNoteWithUser(token,id);
    }
    @GetMapping("search/")
    public List<Notes> searchBasedOnKeyWord(@RequestHeader("Authorization") String token, @RequestParam("q") String keyword){
        return notesManager.searchBasedOnKeyWord(token,keyword);
    }

}
