package com.notesManager.notesManager.service.impl;

import com.notesManager.notesManager.dto.*;
import com.notesManager.notesManager.entity.Notes;
import com.notesManager.notesManager.external.client.UserService;
import com.notesManager.notesManager.repository.NotesRepo;
import com.notesManager.notesManager.service.JwtService;
import com.notesManager.notesManager.service.NotesManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class NotesManagerImpl implements NotesManager {

    @Autowired
    JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    NotesRepo notesRepo;

    public String store(String token, RequestData data) { //create a new note for the authenticated user.
        long idUser = 0;
        if(token != null)
            idUser = getUserId(token);

        if(idUser==0)
            return "token error";

        Notes notes=Notes.builder()
                .title(data.getTitle())
                .description(data.getDescription())
                .userId(idUser)
                .date(new Date())
                .build();
        notesRepo.save(notes);

        return "saved";
    }

    public List<RequestNotesData> listOfNotes(String token){ //get a list of all notes for the authenticated user.
        long idUser = 0;
        if(token != null)
            idUser = getUserId(token);

        if(idUser==0)
            return null;
        List<Notes> notes = notesRepo.findAll();
        List<RequestNotesData> responseNotes=new ArrayList<>();
        for (int i=0;i<notes.size();i++){
            RequestNotesData responseNotes1 = new RequestNotesData();
            responseNotes1.setId(notes.get(i).getId());
            responseNotes1.setTitle(notes.get(i).getTitle());
            responseNotes1.setDescription(notes.get(i).getDescription());
            responseNotes1.setDate(notes.get(i).getDate());
            responseNotes.add(responseNotes1);
        }
        return responseNotes;
    }

    public RequestNotesData getNoteById(String token, long id){  //get a note by ID for the authenticated user.
        long idUser = 0;
        if(token != null)
            idUser = getUserId(token);

        if(idUser==0)
            return null;

        Optional<Notes> notes = notesRepo.findById(id);
        RequestNotesData notesData=new RequestNotesData();
        notesData.setId(notes.get().getId());
        notesData.setTitle(notes.get().getTitle());
        notesData.setDescription(notes.get().getDescription());
        notesData.setDate(notes.get().getDate());
        return notesData;
    }

    public Notes updateNoteById(String token,String id, RequestNoteData requestNoteData){ //update an existing note by ID for the authenticated user.
        long idUser = 0;
        if(token != null)
            idUser = getUserId(token);

        if(idUser==0)
            return null;

        Notes notes  =  notesRepo.findById(Long.parseLong(id)).orElseThrow();
        notes.setTitle(requestNoteData.getTitle());
        notes.setDescription(requestNoteData.getDescription());
        notes.setDate(requestNoteData.getDate());
        if(notes.getDate() == null)
            notes.setDate(new Date());
        notesRepo.save(notes);
        return notes;
    }

    public String deleteNoteById(String token, String id){ // delete a note by ID for the authenticated user.
        long idUser = 0;
        if(token != null)
            idUser = getUserId(token);

        if(idUser==0)
            return null;
        Notes notes= notesRepo.findById(Long.parseLong(id)).orElseThrow();
        notesRepo.delete(notes);
        return "deleted!";
    }

    public ResponseNote shareNoteWithUser(String token, long id){  //share a note with another user for the authenticated user.
        long idUser = 0;
        if(token != null)
            idUser = getUserId(token);
        if(idUser==0)
            return null;
        log.info("user valid");
        Notes notes = notesRepo.findById(id).orElseThrow();
        log.info(notes);
        ResponseNote responseNote = ResponseNote.builder()
                .id(notes.getId())
                .title(notes.getDescription())
                .description(notes.getDescription())
                .date(notes.getDate())
                .userId(notes.getUserId())
                .build();
        return responseNote;
    }

    public List<Notes> searchBasedOnKeyWord(String token, String keyword){ //search for notes based on keywords for the authenticated user
        long idUser = 0;
        if(token != null)
            idUser = getUserId(token);

        if(idUser==0)
            return null;

        return notesRepo.search(keyword);
    }

    public long getUserId(String token){
        String s1 = token.substring(token.indexOf(" ") + 1);
        s1.trim();
        log.info("token :"+s1);
        log.info("token  : >>> "+jwtService.extractUserName(s1));
        String username = jwtService.extractUserName(s1);
        return userService.getUserIdByUsername(username);
    }
}
